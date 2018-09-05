package com.performer.player.comment.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.performer.player.comment.pojo.User;

@Mapper
public interface UserMapper {

    public List<User> getAll();
    public int addUserInfo(User user);
}
