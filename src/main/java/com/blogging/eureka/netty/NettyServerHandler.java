package com.blogging.eureka.netty;

import com.blogging.eureka.model.entity.NettyEntity;
import com.blogging.eureka.service.netty.AbstractNettyService;
import com.blogging.eureka.support.pattern.strategy.FactoryList;
import com.blogging.eureka.support.spring.ApplicationContextCache;
import com.blogging.eureka.support.utils.JsonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author techoneduan
 * @date 2018/12/15
 */

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(NettyServerHandler.class);

    @Autowired
    private FactoryList<AbstractNettyService, String> nettyService;

    @Override
    public void channelRead (ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = readByteBuf(msg);
        if (StringUtils.isBlank(body)) {
            LOG.info("netty request为空");
            return;
        }
        NettyEntity nettyEntity = JsonUtil.toBean(body, NettyEntity.class);
        if (StringUtils.isBlank(nettyEntity.getHeader())) {
            LOG.info("netty request header为空:{}", nettyEntity);
            return;
        }
        ApplicationContextCache.getFactoryListHolder().getNettyService().getBean(nettyEntity.getHeader()).dealRequest(nettyEntity);

        byte[] bytes = "repeat".getBytes();
        ByteBuf re = Unpooled.buffer(bytes.length);
        re.writeBytes(bytes);
        ctx.writeAndFlush(re);
    }

    @Override
    public void channelReadComplete (ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught (ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    public static String readByteBuf (Object msg) {
        String body = null;
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        try {
            body = new String(req, "UTF-8");
        } catch (Exception e) {
            LOG.info("netty 解析byteBuf异常:{}", e);
        }
        return body;
    }
}
