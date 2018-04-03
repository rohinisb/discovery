package com.exercise.discovery.service;

import com.exercise.discovery.bean.InstanceVO;
import com.exercise.discovery.bean.ServiceVO;
import com.exercise.discovery.config.RestConfig;
import com.exercise.discovery.constants.InstanceHealth;
import com.exercise.discovery.constants.ServiceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DiscoverServiceImpl implements DiscoverService {

    private Logger LOGGER = LoggerFactory.getLogger(DiscoverServiceImpl.class);

    @Value("${instance.healthcheck.endpoint}")
    private String healthCheckEndpoint;

    @Value("${response.time.threshold.ms}")
    private long reponseTimeThreshold;

    @Autowired
    RestConfig restConfig;

    @Override
    public List<ServiceVO> getServiceStatus() {
        //Ideally fetch from DB where it is persisted
        Map<String, ServiceVO> registeredServices = RegisterServiceImpl.registeredInstances;
        registeredServices.forEach((serviceName, serviceVO) -> checkServiceHealthStatus(serviceVO));

        return new ArrayList<>(registeredServices.values());
    }

    @Override
    public List<ServiceVO> getServiceStatus(String serviceName) {
        //add logic for this service only
        return null;
    }

    private void updateInstanceHealthStatus(InstanceVO instanceVO) {
       String healthCheckUrl = "http://"+instanceVO.getAddress()+":"+instanceVO.getPort()+healthCheckEndpoint;
       ResponseEntity<String> response;
       try{
           long time = System.currentTimeMillis();
           response = restConfig.restTemplate().getForEntity(healthCheckUrl, String.class);
           long responseTime = System.currentTimeMillis() - time;
           LOGGER.info("Response time of instance "+instanceVO.getId()+" is "+responseTime+" ms.");
           if(response.getStatusCode().is2xxSuccessful() && responseTime < reponseTimeThreshold) {
               instanceVO.setStatus(InstanceHealth.NORMAL);
           } else if (response.getStatusCode().is2xxSuccessful() && responseTime > reponseTimeThreshold) {
               instanceVO.setStatus(InstanceHealth.SLOW);
           } else {
               instanceVO.setStatus(InstanceHealth.ERROR);
           }
       }catch (ResourceAccessException e){
           instanceVO.setStatus(InstanceHealth.TIMEOUT);
       }catch (HttpServerErrorException e){
           instanceVO.setStatus(InstanceHealth.ERROR);
       }
    }

    private void checkServiceHealthStatus(ServiceVO serviceVO) {
        List<String> instanceStatus = new ArrayList<>();
        serviceVO.getInstances().forEach(instanceVO -> {updateInstanceHealthStatus(instanceVO); instanceStatus.add(instanceVO.getStatus().toString());});
        if(instanceStatus.stream().allMatch(InstanceHealth.NORMAL.toString()::equals)){
            serviceVO.setServiceStatus(ServiceStatus.OK);
        } else if (!instanceStatus.contains(InstanceHealth.NORMAL.toString())) {
            serviceVO.setServiceStatus(ServiceStatus.ERROR);
        } else {
            serviceVO.setServiceStatus(ServiceStatus.WARNING);
        }
    }
}
