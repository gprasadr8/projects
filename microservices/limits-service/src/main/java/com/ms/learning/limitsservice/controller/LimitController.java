package com.ms.learning.limitsservice.controller;

import com.ms.learning.limitsservice.configuration.LimitsServiceConfigProperties;
import com.ms.learning.limitsservice.models.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {

    @Autowired
    private LimitsServiceConfigProperties configProperties;
    @GetMapping("/limits")
    public Limits retrieveLimits(){
        return new Limits(configProperties.getMinimum(), configProperties.getMaximum());
    }
}
