package com.exercise.discovery.service;

import com.exercise.discovery.bean.ServiceVO;

import java.util.List;

public interface DiscoverService {

    public List<ServiceVO> getServiceStatus();
    public List<ServiceVO> getServiceStatus(String serviceName);

}
