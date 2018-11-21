package com.performer.player.comment.impl;

import com.performer.player.comment.dao.mapper.CommentMapper;
import com.performer.player.comment.pojo.Comment;
import com.performer.player.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentImpl implements CommentService{

    @Autowired
    private CommentMapper CommentMapper;
    @Override
    public List<Comment> getCommentList(Comment com) {
        return CommentMapper.getCommentList(com);
    }

    @Override
    @Transactional
    public void insert(Comment com) {
        CommentMapper.insertComment(com);
    }

	@Override
	public Integer getMaxFloorNum(Comment com) {
		return CommentMapper.getMaxFloorNum(com);
	}
}
