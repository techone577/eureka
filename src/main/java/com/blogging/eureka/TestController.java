package com.blogging.eureka;

import com.alibaba.fastjson.JSONObject;
import com.blogging.eureka.model.eureka.ServiceInstance;
import com.blogging.eureka.support.eureka.EurekaServerApplication;
import com.blogging.eureka.support.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author techoneduan
 * @date 2018/12/17
 */
@RestController
@RequestMapping("/test")
public class TestController {

//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient client;

    @RequestMapping("/rest")
    public String restTest () {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> a = restTemplate.getForEntity("http://localhost:1001/eureka/apps", String.class);
//        JSONObject jsonObject = JSONObject.parseObject(a.getBody());
//        String str = jsonObject.getJSONObject("applications").getString("application");
        List<ServiceInstance> si = EurekaServerApplication.instancesInfo();
        return "null";
    }
}
