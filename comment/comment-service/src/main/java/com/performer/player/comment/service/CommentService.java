package com.performer.player.comment.service;

import com.performer.player.comment.pojo.Comment;
import com.performer.player.comment.pojo.UserInfo;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentList(Comment com);
    void insert(Comment com);
    Integer getMaxFloorNum(Comment com);
    List<Comment> getReplyContent(Comment com);
    UserInfo getUserInfo(Long user_id);
}
