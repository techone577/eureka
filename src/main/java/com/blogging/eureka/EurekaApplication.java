package com.blogging.eureka;

import com.blogging.eureka.netty.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaServer
@EnableZuulProxy
public class EurekaApplication {
    private static final Logger LOG = LoggerFactory.getLogger(EurekaApplication.class);

    public static void main (String[] args) {
        //启动eureka server
        SpringApplication.run(EurekaApplication.class, args);
        //启动netty server 注意启动顺序 先启动netty监听端口阻塞内置tomcat启动
        LOG.info("netty开始启动...");
        try {
            NettyServer.run();
        }catch(Exception e){
            LOG.info("netty启动失败");
        }
    }
}
