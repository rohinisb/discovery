package com.exercise.discovery.bean;

import com.exercise.discovery.constants.ServiceStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Set;

public class ServiceVO implements Serializable{

    @JsonProperty("ServiceName")
    private String serviceName;

    private ServiceStatus serviceStatus;

    private Set<InstanceVO> instances;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Set<InstanceVO> getInstances() {
        return instances;
    }

    public void setInstances(Set<InstanceVO> instances) {
        this.instances = instances;
    }
}
