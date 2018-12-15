package com.blogging.eureka.netty;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author techoneduan
 * @date 2018/12/15
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead (ChannelHandlerContext ctx, Object msg) throws Exception{
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("NOW is: " + body);

        byte []bytes =  "repeat".getBytes();
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
}
