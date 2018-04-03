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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstanceVO that = (InstanceVO) o;

        if (port != that.port) return false;
        if (!id.equals(that.id)) return false;
        return serviceName.equals(that.serviceName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + port;
        result = 31 * result + serviceName.hashCode();
        return result;
    }
}
