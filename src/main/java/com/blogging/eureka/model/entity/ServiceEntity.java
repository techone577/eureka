package com.blogging.eureka.model.entity;

import java.util.Date;

/**
 * 服务实体类
 */
public class ServiceEntity {

    private Long id;

    /**
     * application name
     */
    private String clientName;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 实例名称（暂无计划）
     */
    private String instanceName;

    /**
     * ip地址
     */
    private String ipAddr;

    /**
     * 可用端口
     */
    private String port;

    /**
     * 路由映射
     */
    private String routeAddr;

    /**
     * 支持的方法类型
     */
    private String method;

    /**
     * 参数列表
     */
    private String param;

    /**
     * 返回值类型
     */
    private String returnValue;

    /**
     * 是否可用
     */
    private Integer enable;

    /**
     * 服务描述
     */
    private String description;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName == null ? null : clientName.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName == null ? null : instanceName.trim();
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr == null ? null : ipAddr.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    public String getRouteAddr() {
        return routeAddr;
    }

    public void setRouteAddr(String routeAddr) {
        this.routeAddr = routeAddr == null ? null : routeAddr.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getParam () {
        return param;
    }

    public void setParam (String param) {
        this.param = param;
    }

    public String getReturnValue () {
        return returnValue;
    }

    public void setReturnValue (String returnValue) {
        this.returnValue = returnValue;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }
}