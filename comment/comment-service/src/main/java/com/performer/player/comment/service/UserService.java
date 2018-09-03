package com.performer.player.comment.service;

import com.performer.player.comment.pojo.User;

import java.util.List;

public interface UserService {

    public List<User> getAll();
    public void insert(User user);
}
