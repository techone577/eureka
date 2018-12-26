package com.blogging.eureka.netty;

import com.blogging.eureka.model.Constants.NettyHeader;
import com.blogging.eureka.model.dto.ServiceInfoDTO;
import com.blogging.eureka.model.entity.NettyReqEntity;
import com.blogging.eureka.model.entity.NettyRespEntity;
import com.blogging.eureka.model.entity.ServiceEntity;
import com.blogging.eureka.model.entity.SubInfoEntity;
import com.blogging.eureka.persistence.ServiceEntityMapper;
import com.blogging.eureka.persistence.SubInfoEntityMapper;
import com.blogging.eureka.support.utils.JsonUtil;
import com.blogging.eureka.support.utils.RedisUtil;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author techoneduan
 * @date 2018/12/18
 * <p>
 * 缓存channel，实现订阅发布模式
 */
@Component
public class NettyChannelGroup {

    private static final Logger LOG = LoggerFactory.getLogger(NettyChannelGroup.class);

    //保存所有channel sub-pub
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Autowired
    private ServiceEntityMapper serviceEntityMapper;

    @Autowired
    private SubInfoEntityMapper subInfoEntityMapper;

    @Autowired
    private RedisUtil redisUtil;

    public static void add (Channel channel) {
        channels.add(channel);
    }

    public static void remove (Channel channel) {
        if (channels.contains(channel))
            channels.remove(channel);
    }

    /**
     * 客户端订阅的服务列表做缓存，每个客户端只更新订阅的服务信息
     */
    public void publish () {
        channels.stream().forEach(item -> {
            pullSubServiceInfo(item, NettyHeader.REGISTRY, UUID.randomUUID().toString());
        });
    }

    /**
     * 拉取订阅的服务列表
     *
     * @param c
     * @param header
     * @param requestId
     */
    public void pullSubServiceInfo (Channel c, String header, String requestId) {
        String subInfo = redisUtil.getString(c.remoteAddress().toString());
        if (StringUtils.isBlank(subInfo)) {
            subInfo = pullSubServiceFromDB(c.remoteAddress().toString());
            if (StringUtils.isBlank(subInfo)) {
                LOG.info("拉取服务列表为空，是否未注册消费者,ipAddr:{}", c.remoteAddress().toString());
                return;
            }
            redisUtil.doCache(c.remoteAddress().toString(), subInfo);
        }
        List<String> subInfoList = JsonUtil.toBean(subInfo, List.class);
        String serviceList = queryServiceInfo(subInfoList);
        NettyRespEntity respEntity = new NettyRespEntity();
        respEntity.setRequestId(requestId);
        respEntity.setRespType(header);
        respEntity.setResponse(serviceList);
        send(c, JsonUtil.toString(respEntity));
    }

    private String pullSubServiceFromDB (String addr) {
        SubInfoEntity entity = subInfoEntityMapper.selectByAddr(addr);
        return Objects.isNull(entity) ? null : entity.getSubServices();
    }

    private String queryServiceInfo (List<String> list) {
        if (null == list || list.size() == 0)
            return null;
        List<ServiceEntity> entities = serviceEntityMapper.selectByName(list);
        List<ServiceInfoDTO> dtos = entities.stream().map(item -> {
            ServiceInfoDTO dto = new ServiceInfoDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
        return JsonUtil.toString(dtos);
    }

    private void send (Channel channel, String msg) {
        if (StringUtils.isBlank(msg))
            return;
        channel.writeAndFlush(NettyServer.getByteBuf(msg));
    }
}
