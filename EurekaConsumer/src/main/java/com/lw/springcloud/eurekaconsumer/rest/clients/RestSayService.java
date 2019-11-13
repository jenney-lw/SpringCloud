package com.lw.springcloud.eurekaconsumer.rest.clients;

import com.lw.springcloud.eurekaconsumer.annotation.RestClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestClient(name = "eureka-client")
public interface RestSayService {

    @GetMapping("/hello")
    String say(@RequestParam("name") String name);

}
