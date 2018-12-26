package com.blogging.eureka.model.dto;

/**
 * @author techoneduan
 * @date 2018/12/20
 *
 * 推送给客户端的服务信息,queryDto
 */
public class ServiceInfoDTO {

    private String serviceName;

    private String ipAddr;

    private String port;

    private String routeAddr;

    private String method;

    public String getServiceName () {
        return serviceName;
    }

    public void setServiceName (String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIpAddr () {
        return ipAddr;
    }

    public void setIpAddr (String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getPort () {
        return port;
    }

    public void setPort (String port) {
        this.port = port;
    }

    public String getRouteAddr () {
        return routeAddr;
    }

    public void setRouteAddr (String routeAddr) {
        this.routeAddr = routeAddr;
    }

    public String getMethod () {
        return method;
    }

    public void setMethod (String method) {
        this.method = method;
    }
}
