package com.blogging.eureka.service.netty;

import com.blogging.eureka.model.Constants.NettyRequestHeader;
import com.blogging.eureka.model.entity.NettyEntity;
import com.blogging.eureka.support.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author techoneduan
 * @date 2018/12/17
 */

@Service
public class RegistryNettyService extends AbstractNettyService {

    private static final Logger LOG = LoggerFactory.getLogger(RegistryNettyService.class);

    @Override
    public void dealRequest (NettyEntity request) {
        LOG.info(JsonUtil.toString(request.getParams()));
    }

    @Override
    public boolean matching (String factor) {
        return NettyRequestHeader.REGISTRY.equals(factor);
    }

}
