package com.blogging.eureka.model.Constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author techoneduan
 * @date 2018/12/17
 *
 * 配置存储 避免static注入不可用
 */
@Component
public class PropertiesHolder {

    @Value("${server.port}")
    private Integer port;

    public Integer getPort () {
        return port;
    }
}
