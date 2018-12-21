package com.blogging.eureka.model.entity;

/**
 * 订阅服务信息实体类
 */
public class SubInfoEntity {

    private Long id;

    /**
     * ip+port地址
     */
    private String remoteAddr;

    /**
     * application name
     */
    private String clientName;

    /**
     * 订阅服务列表
     */
    private String subServices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr == null ? null : remoteAddr.trim();
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName == null ? null : clientName.trim();
    }

    public String getSubServices() {
        return subServices;
    }

    public void setSubServices(String subServices) {
        this.subServices = subServices == null ? null : subServices.trim();
    }
}