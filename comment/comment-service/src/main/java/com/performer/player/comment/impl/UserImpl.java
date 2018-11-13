package com.performer.player.comment.impl;

import com.performer.player.comment.dao.mapper.CommentMapper;
import com.performer.player.comment.pojo.User;
import com.performer.player.comment.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserImpl implements UserService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<User> getAll() {
        System.out.println("UserSImpl getAll");
        return commentMapper.getAll();
    }

    @Override
    @Transactional
    public void insert(User user) {
        commentMapper.addUserInfo(user);
    }
}
