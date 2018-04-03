package com.exercise.discovery.service;

import com.exercise.discovery.bean.InstanceVO;
import com.exercise.discovery.bean.RegisterResponseVO;

public interface RegisterService {

    public RegisterResponseVO addService(InstanceVO instanceVO);
    public RegisterResponseVO updateService(InstanceVO instanceVO);
    public RegisterResponseVO removeService(InstanceVO instanceVO);

}
