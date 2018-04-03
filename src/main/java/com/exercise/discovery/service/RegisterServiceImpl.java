package com.exercise.discovery.service;

import com.exercise.discovery.bean.InstanceVO;
import com.exercise.discovery.bean.RegisterResponseVO;
import com.exercise.discovery.bean.ServiceVO;
import com.exercise.discovery.constants.RegisterStatus;
import com.exercise.discovery.constants.ServiceOperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RegisterServiceImpl implements RegisterService {

    private Logger LOGGER = LoggerFactory.getLogger(RegisterServiceImpl.class);
    public static Map<String, ServiceVO> registeredInstances = new HashMap();

    @Override
    public RegisterResponseVO addService(InstanceVO instanceVO) {
        try{
            if(!validatePayload(instanceVO))
                return new RegisterResponseVO(ServiceOperationType.ADD, RegisterStatus.BAD_REQUEST, "ID/ServiceName/Port cannot be empty");
            if(!registeredInstances.containsKey(instanceVO.getServiceName())) {
                ServiceVO serviceVO = new ServiceVO();
                serviceVO.setServiceName(instanceVO.getServiceName());
                serviceVO.setInstances(Stream.of(instanceVO).collect(Collectors.toSet()));
                registeredInstances.put(instanceVO.getServiceName(), serviceVO);
            } else {
                Set<InstanceVO> instances = registeredInstances.get(instanceVO.getServiceName()).getInstances();
                InstanceVO instance = instances.stream()
                .filter(x -> x.equals(instanceVO)).findAny().orElse(null);
                if(null == instance) {
                    instances.add(instanceVO);
                    registeredInstances.get(instanceVO.getServiceName()).setInstances(instances);
                }
            }
            LOGGER.info("Instance "+ instanceVO.getId() + " registered successfully");

        }catch (Exception e){

            LOGGER.error("Unable to register instance "+instanceVO.getId() + e);
            return new RegisterResponseVO(ServiceOperationType.ADD, RegisterStatus.ERROR, e.getMessage());
        }
        return new RegisterResponseVO(ServiceOperationType.ADD, RegisterStatus.OK, "None");
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

    private boolean validatePayload(InstanceVO instanceVO) {
        if(StringUtils.isEmpty(instanceVO.getServiceName())|| StringUtils.isEmpty(instanceVO.getId()) || StringUtils.isEmpty(instanceVO.getPort()))
            return false;
        return true;
    }

}
