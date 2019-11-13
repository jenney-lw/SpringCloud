package com.lw.springcloud.eurekaconsumer;

import com.lw.springcloud.eurekaconsumer.annotation.EnableRestClients;
import com.lw.springcloud.eurekaconsumer.feign.clients.FeignUserService;
import com.lw.springcloud.eurekaconsumer.feign.clients.FileUploadFeignService;
import com.lw.springcloud.eurekaconsumer.feign.clients.SayService;
import com.lw.springcloud.eurekaconsumer.rest.clients.RestSayService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = {SayService.class, FileUploadFeignService.class, FeignUserService.class})
@EnableRestClients(clients = RestSayService.class)
public class SpringCloudEurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
