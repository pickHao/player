package com.performer.player.comment.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.performer.player.comment.pojo.Comment;

@Mapper
public interface CommentMapper {

    List<Comment> getCommentList(Comment com);
    void insertComment(Comment com);
    Integer getMaxFloorNum(Comment com);
    List<Comment> getReplyContent(Comment com);
}
