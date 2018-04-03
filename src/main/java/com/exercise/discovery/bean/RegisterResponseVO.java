package com.exercise.discovery.bean;

import com.exercise.discovery.constants.RegisterStatus;
import com.exercise.discovery.constants.ServiceOperationType;

import java.io.Serializable;

public class RegisterResponseVO implements Serializable {

    private ServiceOperationType operationType;
    private RegisterStatus registerStatus;
    private String error;

    public RegisterResponseVO(ServiceOperationType operationType, RegisterStatus registerStatus, String error) {
        this.operationType = operationType;
        this.registerStatus = registerStatus;
        this.error = error;
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


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
