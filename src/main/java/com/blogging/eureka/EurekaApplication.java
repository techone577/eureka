package com.blogging.eureka;

import com.blogging.eureka.netty.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaServer
@EnableZuulProxy
@MapperScan(basePackages = "com.blogging.eureka.persistence")
public class EurekaApplication {
    private static final Logger LOG = LoggerFactory.getLogger(EurekaApplication.class);

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
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
