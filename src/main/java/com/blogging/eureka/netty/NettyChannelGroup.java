package com.blogging.eureka.netty;

import com.blogging.eureka.model.entity.NettyRespEntity;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author techoneduan
 * @date 2018/12/18
 */
public class NettyChannelGroup {
    //保存所有channel sub-pub
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void add (Channel channel) {
        channels.add(channel);
    }

    public static void remove (Channel channel) {
        if (channels.contains(channel))
            channels.remove(channel);
    }

    public static void publish (String instanceList) {
        channels.stream().forEach(item->send(item,instanceList));
    }

    private static void send (Channel channel, String msg) {
        channel.writeAndFlush(NettyServer.getByteBuf(msg));
    }
}
