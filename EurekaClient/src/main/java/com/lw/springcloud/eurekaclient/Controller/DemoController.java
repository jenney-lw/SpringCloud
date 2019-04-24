package com.lw.springcloud.eurekaclient.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController {

    @GetMapping("/hello")
    public String index(String name) {
        return name + ", this is spring-cloud-eureka-client";
    }
}
