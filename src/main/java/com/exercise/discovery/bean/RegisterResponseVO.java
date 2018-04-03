package com.exercise.discovery.bean;

import com.exercise.discovery.constants.ServiceOperationType;
import com.exercise.discovery.constants.RegisterStatus;

import java.io.Serializable;

public class RegisterResponseVO implements Serializable {

    private String serviceName;
    private ServiceOperationType operationType;
    private RegisterStatus registerStatus;

    public RegisterResponseVO(String serviceName, ServiceOperationType operationType, RegisterStatus registerStatus) {
        this.serviceName = serviceName;
        this.operationType = operationType;
        this.registerStatus = registerStatus;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ServiceOperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(ServiceOperationType operationType) {
        this.operationType = operationType;
    }

    public RegisterStatus getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(RegisterStatus registerStatus) {
        this.registerStatus = registerStatus;
    }
}
