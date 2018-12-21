package com.blogging.eureka.support.eureka;

import com.blogging.eureka.netty.NettyChannelGroup;
import com.netflix.appinfo.InstanceInfo;
import io.netty.channel.group.ChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author techoneduan
 * @date 2018/12/19
 */

@Component
public class EurekaEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(EurekaEventListener.class);

    @Autowired
    private NettyChannelGroup nettyChannelGroup;

    @EventListener
    public void listen (EurekaInstanceCanceledEvent event) {
        LOG.info(event.getServerId() + "\t" + event.getAppName() + " 服务下线");
        nettyChannelGroup.publish();
    }

    @EventListener
    public void listen (EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        LOG.info(instanceInfo.getAppName() + "进行注册");
    }

    @EventListener
    public void listen (EurekaInstanceRenewedEvent event) {
        System.err.println(event.getServerId() + "\t" + event.getAppName() + " 服务进行续约");
    }

    @EventListener
    public void listen (EurekaRegistryAvailableEvent event) {
        LOG.info("注册中心 启动");
    }

    @EventListener
    public void listen (EurekaServerStartedEvent event) {
        LOG.info("Eureka Server 启动");
    }

}
