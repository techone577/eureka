package com.blogging.eureka.service.netty;


import com.blogging.eureka.model.Constants.NettyHeader;
import com.blogging.eureka.model.entity.NettyReqEntity;
import com.blogging.eureka.model.entity.NettyRespEntity;
import com.blogging.eureka.support.utils.JsonUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author techoneduan
 * @date 2018/12/17
 */
@Service
public class RequestNettyService extends AbstractNettyService {
    @Override
    public void dealRequest (NettyReqEntity request, ChannelHandlerContext ctx) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/test/serviceTest", String.class);
        NettyRespEntity respEntity = new NettyRespEntity();
        respEntity.setRequestId(request.getRequestId());
        respEntity.setResponse(responseEntity.getBody());
        reply(ctx, JsonUtil.toString(respEntity));
    }

    @Override
    public boolean matching (String factor) {
        return NettyHeader.REQUEST.equals(factor);
    }
}
