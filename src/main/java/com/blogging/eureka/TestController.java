package com.blogging.eureka;

import com.blogging.eureka.model.eureka.ServiceInstance;
import com.blogging.eureka.support.eureka.EurekaInstanceCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author techoneduan
 * @date 2018/12/17
 */
@RestController
@RequestMapping("/test")
public class TestController {

////    @Autowired
////    RestTemplate restTemplate;
//
//    @Autowired
//    DiscoveryClient client;
//
//    @RequestMapping("/rest")
//    public String restTest () {
////        RestTemplate restTemplate = new RestTemplate();
////        ResponseEntity<String> a = restTemplate.getForEntity("http://localhost:1001/eureka/apps", String.class);
////        JSONObject jsonObject = JSONObject.parseObject(a.getBody());
////        String str = jsonObject.getJSONObject("applications").getString("application");
//        List<ServiceInstance> si = EurekaInstanceCache.getInstanceCache();
//        return "null";
//    }
}
