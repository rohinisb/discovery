package com.exercise.discovery.bean;

import com.exercise.discovery.constants.InstanceHealth;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class InstanceVO {

    private String id;
    private int port;
    private InstanceHealth status;
    private String serviceName;

    @JsonIgnore
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InstanceHealth getStatus() {
        return status;
    }

    public void setStatus(InstanceHealth status) {
        this.status = status;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
