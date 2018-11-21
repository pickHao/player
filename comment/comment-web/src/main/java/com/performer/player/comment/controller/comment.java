package com.performer.player.comment.controller;

import com.performer.player.comment.entity.CommentRequestBodyData;
import com.performer.player.comment.impl.CommentImpl;
import com.performer.player.comment.pojo.Comment;
import com.performer.player.comment.util.Const;
import com.performer.player.common.utils.ResultUtil;
import com.performer.player.common.utils.ReturnMsg;
import com.performer.player.common.utils.StringUtils;

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
    	@ApiImplicitParam(paramType = "query", name = "usid", value = "用户id", required = true, dataType = "Long"),
    	@ApiImplicitParam(paramType = "query", name = "reid", value = "回复的楼层id", required = false, dataType = "String"),
    	@ApiImplicitParam(paramType = "query", name = "thty", value = "评论的主题类型", required = true, dataType = "int"),
    	@ApiImplicitParam(paramType = "query", name = "thid", value = "评论的帖子对象id", required = true, dataType = "Long"),
    	@ApiImplicitParam(paramType = "query", name = "reuid", value = "回复的用户id", required = false, dataType = "String")
    })
    public ReturnMsg addComment(@RequestBody CommentRequestBodyData request){
    	if(paramterCheck(request)){
    		return ResultUtil.error(-101, "参数错误");
    	}
        Comment com = new Comment();
        com.setContent(request.getContent());
        com.setCreate_time(request.getCreate_time());
//      点赞默认为0
        com.setNumber_of_praise(0);
        com.setUser_id(request.getUser_id());
        com.setReply_id(request.getReply_id());
        com.setReply_user_id(request.getReply_user_id());
        com.setTheme_id(request.getTheme_id());
        com.setTheme_type(Const.valueOf(request.getTheme_type()).getValue());
        commentImpl.insert(com);
        Map<String, String> a = new HashMap<String, String>();
        a.put("da", "success");
        return ResultUtil.success(a);
    }

    /**
     * 参数检查
     * @param request
     * @return 
     */
	private boolean paramterCheck(CommentRequestBodyData request) {
		if(StringUtils.isNullOrSpace(request.getContent())){
			return true;
		}
		if(StringUtils.isNullOrSpace(request.getCreate_time())){
			return true;
		}
		
		if(Const.valueOf(request.getTheme_type())==null){
			return true;
		}
		if(request.getTheme_id()==null){
			return true;
		}
		if(request.getUser_id()==null){
			return true;
		}
		return false;
	}
}
