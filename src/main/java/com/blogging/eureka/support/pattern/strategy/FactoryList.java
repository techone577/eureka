package com.blogging.eureka.support.pattern.strategy;


import java.util.List;

public interface FactoryList<E extends MatchingBean<K>, K> extends List<E> {

    E getBean (K factor);
    
    <B> List<B> map (Function<E, B> func);
}
