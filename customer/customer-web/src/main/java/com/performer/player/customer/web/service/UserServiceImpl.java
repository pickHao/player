package com.performer.player.customer.web.service;


import com.performer.player.customer.web.dao.UserMapper;
import com.performer.player.customer.web.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public User queryUserInfoByName(String account) {
        User user = userMapper.getUserInfo(account);
        if(user == null){
            return null;
        }
        return user;
    }
}
