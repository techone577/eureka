package com.blogging.eureka.service.netty;


import com.blogging.eureka.model.Constants.NettyRequestHeader;
import com.blogging.eureka.model.entity.NettyEntity;
import org.springframework.stereotype.Service;

/**
 * @author techoneduan
 * @date 2018/12/17
 */
@Service
public class RequestNettyService extends AbstractNettyService {
    @Override
    public void dealRequest (NettyEntity request) {

    }

    @Override
    public boolean matching (String factor) {
        return NettyRequestHeader.REQUEST.equals(factor);
    }
}
