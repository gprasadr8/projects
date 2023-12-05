package com.ms.learning.limitsservice.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("limits-service")
public class LimitsServiceConfigProperties {

    private int minimum;
    private int maximum;
}
