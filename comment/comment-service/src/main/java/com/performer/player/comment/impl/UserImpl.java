package com.performer.player.comment.impl;

import com.performer.player.comment.dao.mapper.UserMapper;
import com.performer.player.comment.pojo.User;
import com.performer.player.comment.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserImpl implements UserService {

	@Autowired
    private UserMapper userMapper;
    public List<User> getAll() {
        System.out.println("UserSImpl getAll");
        return userMapper.getAll();
    }
    @Transactional
    public void insert(User user) {
        userMapper.addUserInfo(user);
    }
}
