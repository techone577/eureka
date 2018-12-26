package com.blogging.eureka.service.netty;

import com.blogging.eureka.model.Constants.NettyHeader;
import com.blogging.eureka.model.entity.NettyReqEntity;
import com.blogging.eureka.netty.NettyChannelGroup;
import com.blogging.eureka.support.utils.RedisUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author techoneduan
 * @date 2018/12/20
 */
@Service
public class PullNettyService extends AbstractNettyService {

    @Autowired
    private NettyChannelGroup channelGroup;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void dealRequest (NettyReqEntity request, ChannelHandlerContext ctx) {
        redisUtil.remove(ctx.channel().remoteAddress().toString());
        channelGroup.pullSubServiceInfo(ctx.channel(), NettyHeader.PULL, request.getRequestId());
    }

    @Override
    public boolean matching (String factor) {
        return NettyHeader.PULL.equals(factor);
    }
}
