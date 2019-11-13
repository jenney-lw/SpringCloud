package com.lw.springcloud.eurekaconsumer.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("eureka-client")
public interface SayService {

    @RequestMapping("/hello")
    String say(@RequestParam("name") String name);

}
