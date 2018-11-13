package com.performer.player.comment.dao.mapper;


import java.util.List;

import com.performer.player.comment.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import com.performer.player.comment.pojo.User;

@Mapper
public interface CommentMapper {

    List<User> getAll();
    int addUserInfo(User user);
    List<Comment> getCommentList();
    void insertComment(Comment com);
}
