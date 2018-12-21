package com.blogging.eureka.service.netty;

import com.blogging.eureka.model.Constants.NettyHeader;
import com.blogging.eureka.model.entity.NettyReqEntity;
import com.blogging.eureka.model.entity.ServiceEntity;
import com.blogging.eureka.model.entity.SubInfoEntity;
import com.blogging.eureka.model.eureka.RegistryInfo;
import com.blogging.eureka.netty.NettyChannelGroup;
import com.blogging.eureka.service.eureka.BSPService;
import com.blogging.eureka.support.utils.JsonUtil;
import com.blogging.eureka.support.utils.RedisUtil;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author techoneduan
 * @date 2018/12/17
 */

@Service
public class RegistryNettyService extends AbstractNettyService {

    private static final Logger LOG = LoggerFactory.getLogger(RegistryNettyService.class);

    @Autowired
    private BSPService bspService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private NettyChannelGroup channelGroup;

    @Override
    public void dealRequest (NettyReqEntity request, ChannelHandlerContext ctx) {
        LOG.info(JsonUtil.toString(request.getParams()));
        //TODO
        registryService(request.getParams(), ctx);
        //TODO pub
        channelGroup.publish();
    }

    @Override
    public boolean matching (String factor) {
        return NettyHeader.REGISTRY.equals(factor);
    }

    private void registryService (Object obj, ChannelHandlerContext ctx) {
        //TODO 去重
        /**
         * 注册信息
         */
        RegistryInfo registryInfo = JsonUtil.toBean(obj, RegistryInfo.class);
        List<ServiceEntity> serviceEntities = registryInfo.getServiceConfigs().stream().map(item -> {
            ServiceEntity entity = new ServiceEntity();
            entity.setClientName(registryInfo.getClientName());
            entity.setIpAddr(registryInfo.getIpaddr());
            entity.setPort(registryInfo.getPort());
            entity.setServiceName(item.getName());
            entity.setInstanceName("test");
            entity.setMethod(item.getMethod());
            entity.setRouteAddr(item.getMapping());
            return entity;
        }).collect(Collectors.toList());
        bspService.addService(serviceEntities);
        /**
         * 订阅信息
         */
        SubInfoEntity entity = new SubInfoEntity();
        entity.setClientName(registryInfo.getClientName());
        entity.setRemoteAddr(ctx.channel().remoteAddress().toString());
        entity.setSubServices(JsonUtil.toString(registryInfo.getSubscribeServices()));
        bspService.addSubInfo(entity);
        redisUtil.doCache(entity.getRemoteAddr(), entity.getSubServices());
    }


}
