package com.performer.player.comment.impl;

import com.performer.player.comment.dao.mapper.UserMapper;
import com.performer.player.comment.pojo.User;
import com.performer.player.comment.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    public List<User> getAll() {
        System.out.println("UserSImpl getAll");

        return userMapper.getAll();
    }
}
