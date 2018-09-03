package com.performer.player.comment.dao.mapper;


import com.performer.player.comment.pojo.User;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public List<User> getAll();
    public int addUserInfo(User user);
}
