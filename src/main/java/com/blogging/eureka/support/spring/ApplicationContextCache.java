package com.blogging.eureka.support.spring;

import com.blogging.eureka.model.Constants.PropertiesHolder;
import com.blogging.eureka.service.netty.FactoryListHolder;
import com.blogging.eureka.support.utils.RedisUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author techoneduan
 * @date 2018/12/17
 */
@Component
public class ApplicationContextCache implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext (ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext () {
        return applicationContext;
    }

    public static PropertiesHolder getPropertiesHolder () {
        if (null != applicationContext)
            return (PropertiesHolder) applicationContext.getBean("propertiesHolder");
        return null;
    }

    public static FactoryListHolder getFactoryListHolder () {
        if (null != applicationContext)
            return (FactoryListHolder) applicationContext.getBean("factoryListHolder");
        return null;
    }

    public static RedisUtil getRedisUtil () {
        if (null != applicationContext)
            return (RedisUtil) applicationContext.getBean("redisUtil");
        return null;
    }
}
