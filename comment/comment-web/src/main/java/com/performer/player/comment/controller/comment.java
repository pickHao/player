package com.performer.player.comment.controller;

import com.performer.player.comment.impl.CommentImpl;
import com.performer.player.comment.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class comment {
    @Autowired
    private CommentImpl commentImpl;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<Comment> getAll(){
        List<Comment> com = commentImpl.getCommentList();
        return com;
    }

    @RequestMapping(value = "addComment",method = RequestMethod.POST)
    public String addComment(Comment request){
        Comment com = new Comment();
        com.setContent(request.getContent());
        com.setCreate_time(request.getCreate_time());
//        点赞默认为0/回复默认为空
        com.setNumber_of_praise(0);
        com.setUsername(request.getUsername());
        commentImpl.insert(com);
        return "success";
    }
}
