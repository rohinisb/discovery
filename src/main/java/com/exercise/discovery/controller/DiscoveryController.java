package com.exercise.discovery.controller;

import com.exercise.discovery.bean.InstanceVO;
import com.exercise.discovery.bean.RegisterResponseVO;
import com.exercise.discovery.bean.ServiceVO;
import com.exercise.discovery.service.DiscoverService;
import com.exercise.discovery.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class DiscoveryController {

    @Autowired
    RegisterService registerService;

    @Autowired
    DiscoverService discoverService;

    @RequestMapping(path = "/register", method=RequestMethod.POST,produces="application/json",consumes="application/json")
    public RegisterResponseVO register(@RequestBody InstanceVO instanceVO, HttpServletRequest request) {
        instanceVO.setAddress(request.getRemoteAddr());
        return registerService.addService(instanceVO);
    }

    @RequestMapping(path = "/discover", method=RequestMethod.GET)
    public List<ServiceVO> discover(@RequestParam(value="service", required = false) Optional<String> serviceName) {
        return serviceName.isPresent() ? discoverService.getServiceStatus(serviceName.get()) : discoverService.getServiceStatus();
    }
}
