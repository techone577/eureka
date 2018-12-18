package com.blogging.eureka.service.netty;

import com.blogging.eureka.model.Constants.NettyHeader;
import com.blogging.eureka.model.entity.NettyReqEntity;
import com.blogging.eureka.model.entity.NettyRespEntity;
import com.blogging.eureka.netty.NettyChannelGroup;
import com.blogging.eureka.support.eureka.EurekaInstanceCache;
import com.blogging.eureka.support.utils.JsonUtil;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author techoneduan
 * @date 2018/12/17
 */

@Service
public class RegistryNettyService extends AbstractNettyService {

    private static final Logger LOG = LoggerFactory.getLogger(RegistryNettyService.class);

    @Override
    public void dealRequest (NettyReqEntity request, ChannelHandlerContext ctx) {
        LOG.info(JsonUtil.toString(request.getParams()));
        //TODO
        registryService();
        //TODO pub
        NettyRespEntity respEntity = new NettyRespEntity();
        respEntity.setRequestId(request.getRequestId());
        respEntity.setRespType(NettyHeader.REGISTRY);
        respEntity.setResponse(JsonUtil.toString(EurekaInstanceCache.getInstanceCache()));
        NettyChannelGroup.publish(JsonUtil.toString(respEntity));
    }

    @Override
    public boolean matching (String factor) {
        return NettyHeader.REGISTRY.equals(factor);
    }

    private void registryService () {

    }

}
