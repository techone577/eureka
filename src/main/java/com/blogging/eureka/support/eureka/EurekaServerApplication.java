package com.blogging.eureka.support.eureka;

import com.alibaba.fastjson.JSONObject;
import com.blogging.eureka.model.eureka.ServiceInstance;
import com.blogging.eureka.support.spring.ApplicationContextCache;
import com.blogging.eureka.support.utils.JsonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author techoneduan
 * @date 2018/12/17
 */
public class EurekaServerApplication {

    //TODO 对实例做缓存
    public static List<ServiceInstance> instancesInfo () {
        RestTemplate restTemplate = new RestTemplate();
        Integer port = ApplicationContextCache.getPropertiesHolder().getPort();
        String restApi = "http://localhost:" + port + "/eureka/apps";
        ResponseEntity<String> applications = restTemplate.getForEntity(restApi, String.class);
        String application = JSONObject.parseObject(applications.getBody()).getJSONObject("applications").getString("application");
        application = application.replace("$", "port");
        application = application.replace("@enable", "enable");
        List<ServiceInstance> instanceList = JsonUtil.toList(application, ServiceInstance.class);
        return instanceList;
    }
}
