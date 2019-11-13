package com.lw.springcloud.eurekaconsumer.feign.clients;

import com.lw.springcloud.eurekaconsumer.Biz.entity.UserReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("eureka-client")
public interface FeignUserService {

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    String addUser(@RequestBody  UserReq user);

}
