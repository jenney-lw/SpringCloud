package com.lw.springcloud.eurekaclient.Controller;

import com.lw.springcloud.eurekaclient.model.UserReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class UserController {

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@RequestBody UserReq userReq, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("oauthToken");
        return userReq.getName() + ", token=" + token + ", hello!";
    }
}
