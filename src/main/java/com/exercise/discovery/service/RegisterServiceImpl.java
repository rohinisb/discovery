package com.exercise.discovery.service;

import com.exercise.discovery.bean.InstanceVO;
import com.exercise.discovery.bean.RegisterResponseVO;
import com.exercise.discovery.bean.ServiceVO;
import com.exercise.discovery.constants.RegisterStatus;
import com.exercise.discovery.constants.ServiceOperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RegisterServiceImpl implements RegisterService {

    private Logger LOGGER = LoggerFactory.getLogger(RegisterServiceImpl.class);
    public static Map<String, ServiceVO> registeredInstances = new HashMap();

    @Override
    public RegisterResponseVO addService(InstanceVO instanceVO) {
        try{
            if(!registeredInstances.containsKey(instanceVO.getServiceName())) {
                ServiceVO serviceVO = new ServiceVO();
                serviceVO.setServiceName(instanceVO.getServiceName());
                serviceVO.setInstances(Stream.of(instanceVO).collect(Collectors.toSet()));
                registeredInstances.put(instanceVO.getServiceName(), serviceVO);
            } else {
                registeredInstances.get(instanceVO.getServiceName()).getInstances().add(instanceVO);
            }
        }catch (Exception e){
            LOGGER.error("Unable to register instances "+e);
            return new RegisterResponseVO(instanceVO.getServiceName(), ServiceOperationType.ADD, RegisterStatus.FAILED);
        }
        return new RegisterResponseVO(instanceVO.getServiceName(), ServiceOperationType.ADD, RegisterStatus.SUCCESS);
    }

    @Override
    public RegisterResponseVO updateService(InstanceVO instanceVO) {
        //logic to update a service
        return null;
    }

    @Override
    public RegisterResponseVO removeService(InstanceVO instanceVO) {
        //logic to update a service
        return null;
    }

}
