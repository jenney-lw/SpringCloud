package com.lw.springcloud.eurekaconsumer.Controller;


import com.lw.springcloud.eurekaconsumer.Biz.entity.User;
import com.lw.springcloud.eurekaconsumer.Biz.entity.UserReq;
import com.lw.springcloud.eurekaconsumer.Biz.entity.UserResp;
import com.lw.springcloud.eurekaconsumer.Biz.service.IUserService;
import com.lw.springcloud.eurekaconsumer.Exception.CommonException;
import com.lw.springcloud.eurekaconsumer.feign.clients.FeignUserService;
import com.lw.springcloud.eurekaconsumer.feign.clients.FileUploadFeignService;
import com.lw.springcloud.eurekaconsumer.feign.clients.SayService;
import com.lw.springcloud.eurekaconsumer.rest.clients.RestSayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@Api(value="用户模块")
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    SayService sayService;

    @Autowired
    RestSayService restSayService;

    @Autowired
    FileUploadFeignService fileUploadFeignService;

    @Autowired
    FeignUserService feignUserService;

    @PostMapping("add")
    public Map<String, String> addUser(@Valid @RequestBody UserReq userReq) {
        User user = new User();
        user.setCode(userReq.getCode());
        user.setName(userReq.getName());

        userService.save(user);

        Map<String, String> result = new HashMap<String, String>();
        result.put("respCode", "200");
        result.put("respMsg", "添加成功:" + user);
        return result;
    }

    @GetMapping("list")
    public Map<String, String> pageUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {

        userService.listUser(pageNum, pageSize);

        Map<String, String> result = new HashMap<String, String>();
        result.put("respCode", "200");
        result.put("list", null);
        return result;
    }

    @GetMapping("/get/{id}")
    public Map<String, Object> getUser(@PathVariable("id") String id) {
        User user = userService.getById(id);
        if (user == null) {
            throw new CommonException("01001", "用户" + id + "未找到");
        }
        UserResp resp = new UserResp();
        resp.setId(String.valueOf(user.getId()));
        resp.setName(user.getName());
        resp.setCode(user.getCode());
//        resp.setStatus(user.getStatus());

        Map<String, Object> result = new HashMap<>();
        result.put("respCode", "200");
        result.put("respMsg", "查找成功");
        result.put("data", resp);
        return result;
    }

    @GetMapping("/feign/say")
    public String feignSay(@RequestParam("name") String name) {
        return sayService.say(name);
    }

    @GetMapping("/rest/say")
    public String restSay(@RequestParam("name") String name) {
        return restSayService.say(name);
    }

    @PostMapping("/feign/upload")
    @ApiOperation(value = "文件上传", notes = "请选择文件上传" )
    public String imageUpload(@ApiParam(value="文件上传",required = true) MultipartFile file) throws  Exception {
        return fileUploadFeignService.fileUpload(file);
    }

    @RequestMapping(value = "/feign/add", method = RequestMethod.POST)
    public String feignAddUser(@RequestBody @ApiParam(name = "用户", value = "传入json格式", required = true) UserReq user) {
        return feignUserService.addUser(user);
    }

}
