package com.blogging.eureka.support.threadPool;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author techoneduan
 * @date 2018/12/18
 *
 * rest 线程池
 */

@Repository
public class RestPoolInit implements InitializingBean {

    public static ExecutorService restPool = null;

    @Override
    public void afterPropertiesSet () throws Exception {
        restPool = new ThreadPoolExecutor(8, 16, 30L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(30));
    }
}
