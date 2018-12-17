package com.blogging.eureka.support.pattern.strategy;

/**
 * bean匹配
 * @author xiehui
 *
 */
public interface MatchingBean<T> {

    boolean matching (T factor);
    
}
