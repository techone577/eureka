package com.blogging.eureka.service.netty;


import com.blogging.eureka.model.entity.NettyEntity;
import com.blogging.eureka.support.pattern.strategy.MatchingBean;

/**
 * @author techoneduan
 * @date 2018/12/17
 */
public abstract class AbstractNettyService implements MatchingBean<String> {

    public abstract void dealRequest (NettyEntity request);
}
