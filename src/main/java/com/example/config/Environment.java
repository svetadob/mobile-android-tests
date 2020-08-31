package com.example.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Environment {
    @Value("${platformName:Android}")
    private String platformName;
    @Value("${platformVersion:9.0}")
    private String platformVersion;
    @Value("${deviceName}")
    private String deviceName;
    @Value("${app}")
    private String app;
    @Value("${fullReset:true}")
    private boolean fullResetApp;
    @Value("${testUser}")
    private String testUser;
    @Value("${testUserPassword}")
    private String testUserPassword;
}
