package com.performer.player.comment.service;

import com.performer.player.comment.pojo.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    void insert(User user);
}
