package com.performer.player.comment.service;

import com.performer.player.comment.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentList(Comment com);
    void insert(Comment com);
    Integer getMaxFloorNum(Comment com);
}
