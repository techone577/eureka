package com.blogging.eureka.service.netty;


import com.blogging.eureka.model.entity.NettyReqEntity;
import com.blogging.eureka.support.pattern.strategy.MatchingBean;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author techoneduan
 * @date 2018/12/17
 */
public abstract class AbstractNettyService implements MatchingBean<String> {

    public abstract void dealRequest (NettyReqEntity request, ChannelHandlerContext ctx);

    public void reply (ChannelHandlerContext ctx, String str) {
        ByteBuf MSG;
        byte[] req = str.getBytes();
        MSG = Unpooled.buffer(req.length);
        MSG.writeBytes(req);
        ctx.writeAndFlush(MSG);
    }
}
