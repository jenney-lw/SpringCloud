package com.lw.springcloud.eurekaconsumer.Biz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lw.springcloud.eurekaconsumer.Biz.entity.User;
import com.lw.springcloud.eurekaconsumer.Biz.mapper.UserMapper;
import com.lw.springcloud.eurekaconsumer.Biz.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

//    @Autowired
//    public UserMapper userMapper;

    @Override
    public List<User> listUser(int pageNum, int pageSize) {
//        return userMapper.sele(new Page<User>(pageNum, pageSize), null);
        return null;
    }
}
