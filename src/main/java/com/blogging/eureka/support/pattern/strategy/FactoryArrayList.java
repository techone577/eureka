package com.blogging.eureka.support.pattern.strategy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.OrderComparator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FactoryArrayList<E extends MatchingBean<K>, K> extends ArrayList<E> implements FactoryList<E, K>,InitializingBean {

    private static final long serialVersionUID = 5705342394882249201L;

    public FactoryArrayList () {
        super();
    }
    
    public FactoryArrayList (int size) {
        super(size);
    }
    
    public E getBean(K factor) {
        Iterator<E> itr = iterator();
        while(itr.hasNext()) {
            E beanMatch = itr.next();
            if(beanMatch.matching(factor)) {
                return beanMatch;
            }
        }
        return null;
    }

    public void afterPropertiesSet() throws Exception {
        if (!isEmpty()) {
            Object[] a = toArray();
            OrderComparator.sort(a);
            ListIterator i = listIterator();
            for (int j=0; j<a.length; j++) {
                i.next();
                i.set(a[j]);
            }
        }
    }

	@Override
	public <B> List<B> map(Function<E, B> func) {
		List<B> ret = new ArrayList<B>();
		Iterator<E> itr = iterator();
        while(itr.hasNext()) {
            E service = itr.next();
            B result = func.invoking(service);
            if(result!=null) {
            		ret.add(result);
            }
        }
		return ret;
	}
       
}
