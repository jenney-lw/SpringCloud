package com.lw.dubbo.dubboserver.service.impl;

import com.lw.dubbo.dubboserver.service.ISayHello;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value = "${demo.service.version}")
public class DefaultSayHello implements ISayHello {

    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String sayHello(String message) {
        return String.format("[%s] : Hello, %s", serviceName, message);
    }
}
