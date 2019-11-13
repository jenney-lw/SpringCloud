package com.lw.springcloud.eurekaconsumer.Biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lw.springcloud.eurekaconsumer.Biz.entity.User;

import java.util.List;


public interface IUserService extends IService<User> {

    public List<User> listUser(int pageNum, int pageSize);

}
