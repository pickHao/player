package com.performer.player.comment.controller;

import com.performer.player.comment.entity.CommentRequestBodyData;
import com.performer.player.comment.impl.CommentImpl;
import com.performer.player.comment.pojo.Comment;
import com.performer.player.common.utils.ResultUtil;
import com.performer.player.common.utils.ReturnMsg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value="comment|评论相关接口")
public class comment {
    @Autowired
    private CommentImpl commentImpl;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有评论信息", notes = "不需要添加参数")
    public List<Comment> getAll(){
        List<Comment> com = commentImpl.getCommentList();
        return com;
    }

    @RequestMapping(value = "/addComment",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="评论", notes = "单纯评论，默认点赞数为0，目前没有回复")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "query", name = "co", value = "评论内容", required = true, dataType = "String"),
    	@ApiImplicitParam(paramType = "query", name = "crti", value = "创建时间", required = true, dataType = "String"),
    	@ApiImplicitParam(paramType = "query", name = "usna", value = "用户名", required = true, dataType = "String"),
    	@ApiImplicitParam(paramType = "query", name = "reid", value = "回复id", required = false, dataType = "String"),
    })
    public ReturnMsg addComment(@RequestBody CommentRequestBodyData request){
        Comment com = new Comment();
        com.setContent(request.getContent());
        com.setCreate_time(request.getCreate_time());
//        点赞默认为0/回复默认为空
        com.setNumber_of_praise(0);
        com.setUsername(request.getUsername());
        commentImpl.insert(com);
        Map<String, String> a = new HashMap<String, String>();
        a.put("da", "success");
        return ResultUtil.success(a);
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "this is test";
    }
}
