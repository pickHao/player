package com.performer.player.customer.service;


import com.customer.dao.data.User;
import com.customer.dao.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User queryUserInfoByName(String account, String password) {

        User user = userMapper.getUserInfo(account,password);

        if(user == null){

            return null;

        }

        return user;

    }

}
