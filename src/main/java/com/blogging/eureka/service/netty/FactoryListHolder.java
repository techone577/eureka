package com.blogging.eureka.service.netty;

import com.blogging.eureka.service.netty.AbstractNettyService;
import com.blogging.eureka.support.pattern.strategy.FactoryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author techoneduan
 * @date 2018/12/17
 */

@Component
public class FactoryListHolder {
    @Autowired
    private FactoryList<AbstractNettyService, String> nettyService;

    public FactoryList<AbstractNettyService, String> getNettyService () {
        return nettyService;
    }
}
