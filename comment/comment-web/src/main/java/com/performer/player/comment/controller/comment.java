package com.performer.player.comment.controller;

import com.performer.player.comment.entity.CommentListResponseBodyData;
import com.performer.player.comment.entity.CommentRequestBodyData;
import com.performer.player.comment.entity.PraiseRequestBodyData;
import com.performer.player.comment.entity.replyContent;
import com.performer.player.comment.entity.userInfo;
import com.performer.player.comment.impl.CommentImpl;
import com.performer.player.comment.pojo.Comment;
import com.performer.player.comment.pojo.UserInfo;
import com.performer.player.common.utils.ResultUtil;
import com.performer.player.common.utils.ReturnMsg;
import com.performer.player.common.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Api(value="comment|评论相关接口")
public class comment {
    @Autowired
    private CommentImpl commentImpl;

    @RequestMapping(value = "/{themeType}/{themeId}/getAll",method = RequestMethod.GET)
    @ApiOperation(value = "获取某个视频评论信息")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "path", name = "themeType", value = "评论的主题类型", required = true, dataType = "String"),
    	@ApiImplicitParam(paramType = "path", name = "themeId", value = "评论的帖子对象id", required = true, dataType = "Long"),
    })
    public List<CommentListResponseBodyData> getAll(@PathVariable("themeId") Long theme_id, @PathVariable("themeType") String theme_type){
    	Comment com = new Comment();
    	com.setTheme_id(theme_id);
    	com.setTheme_type(theme_type);
        List<Comment> coms = commentImpl.getCommentList(com);
        List<CommentListResponseBodyData> res = new ArrayList<CommentListResponseBodyData>();
        for(Comment info:coms) {
        	CommentListResponseBodyData resData = new CommentListResponseBodyData();
        	resData.setCommentId(info.getComment_id());
        	List<replyContent> replyContents = getReply(info.getComment_id(),theme_id,theme_type);
        	userInfo userinfo = getUserInfo(info.getUser_id());
        	resData.setContent(info.getContent());
        	resData.setCreateTime(info.getCreate_time());
        	resData.setNumberOfPraise(info.getNumber_of_praise());
        	resData.setThemeId(info.getTheme_id());
        	resData.setThemeType(info.getTheme_type());
        	resData.setUserInfo(userinfo);
        	resData.setReplyContent(replyContents);
        	res.add(resData);
        }
        return res;
    }

    /**
     * 获取指定楼层的回复评论
     * @param comment_id
     * @param theme_type 
     * @param theme_id 
     * @return
     */
    private List<replyContent> getReply(Integer comment_id, Long theme_id, String theme_type) {
    	Comment com = new Comment();
    	com.setComment_id(comment_id);
    	com.setTheme_id(theme_id);
    	com.setTheme_type(theme_type);
    	List<Comment> coms = commentImpl.getReplyContent(com);
    	List<replyContent> replys = new ArrayList<replyContent>();
    	for(Comment info:coms) {
    		replyContent reply = new replyContent();
    		userInfo user = getUserInfo(info.getUser_id());
    		userInfo replyUser = getUserInfo(info.getReply_user_id());
    		reply.setContent(info.getContent());
    		reply.setCreateTime(info.getCreate_time());
    		reply.setNumberOfPraise(info.getNumber_of_praise());
    		reply.setUserInfo(user);
    		reply.setReplyUserInfo(replyUser);
    		replys.add(reply);
    	}
    	return replys;
	}

    /**
     * 获取用户信息
     * @param user_id
     * @return
     */
	private userInfo getUserInfo(Long user_id) {
		UserInfo info = commentImpl.getUserInfo(user_id);
		userInfo user = new userInfo();
		user.setUserId(user_id);
		user.setUserName(info.getUser_name());
		user.setUserImage(info.getUser_image());
		return user;
	}

	@RequestMapping(value = "/addComment",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="评论", notes = "单纯评论，默认点赞数为0")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "query", name = "content", value = "评论内容", required = true, dataType = "String"),
    	@ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "Long"),
    	@ApiImplicitParam(paramType = "query", name = "replyId", value = "回复的楼层id", required = false, dataType = "Integer"),
    	@ApiImplicitParam(paramType = "query", name = "themeType", value = "评论的主题类型", required = true, dataType = "String"),
    	@ApiImplicitParam(paramType = "query", name = "themeId", value = "评论的帖子对象id", required = true, dataType = "Long"),
    	@ApiImplicitParam(paramType = "query", name = "replyUserId", value = "回复的用户id", required = false, dataType = "Long"),
    	@ApiImplicitParam(paramType = "query", name = "isReply", value = "是否是非回复评论,默认0是评论，1是回复", required = false, dataType = "boolean")
    })
    public ReturnMsg<?> addComment(@RequestBody CommentRequestBodyData request){
    	if(paramterCheck(request)){
    		return ResultUtil.error(-101, "参数错误");
    	}
    	Comment com = new Comment();
    	if(!request.getIsReply()) {
    		if(request.getReplyId()!=null||request.getReplyUserId()!=null) {
    			return ResultUtil.error(-102, "数据冲突，当前为非回复评论，却有回复id");
    		}
    		Integer num = searchMaxFloor(request.getThemeId(),request.getThemeType());
    		com.setComment_id(num);
    		com.setIsReply(false);
    	}else{
    		if(request.getReplyId()==null||request.getReplyUserId()==null) {
    			return ResultUtil.error(-103, "数据冲突，当前为回复评论，缺少回复对象id和楼层数");
    		}
    		com.setComment_id(request.getReplyId());
    		com.setIsReply(request.getIsReply());
    	}
    	
        com.setContent(request.getContent());
//      点赞默认为0
        com.setNumber_of_praise(0);
        com.setUser_id(request.getUserId());
        com.setReply_id(request.getReplyId());
        com.setReply_user_id(request.getReplyUserId());
        com.setTheme_id(request.getThemeId());
        com.setTheme_type(request.getThemeType());
        commentImpl.insert(com);
        Map<String, String> a = new HashMap<String, String>();
        a.put("da", "success");
        return ResultUtil.success(a);
    }
    

    @RequestMapping(value = "/addPraise",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="点赞", notes = "根据被点赞的评论id")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "query", name = "id", value = "评论id", required = true, dataType = "Long"),
    	@ApiImplicitParam(paramType = "query", name = "usid", value = "用户id", required = true, dataType = "Long"),
    	@ApiImplicitParam(paramType = "query", name = "prfid", value = "被点赞的楼层id", required = false, dataType = "Integer"),
    	@ApiImplicitParam(paramType = "query", name = "pruid", value = "被点赞的用户id", required = true, dataType = "Long"),
    })
    public ReturnMsg<?> addPraise(@RequestBody PraiseRequestBodyData request){
    	if(paramterCheck(request)){
    		return ResultUtil.error(-101, "参数错误");
    	}
    	
    	Map<String, String> a = new HashMap<String, String>();
        a.put("da", "success");
        return ResultUtil.success(a);
    }

    /**
     * 查询最大楼层
     * @param theme_id
     * @param theme_type
     * @return
     */
	private Integer searchMaxFloor(Long theme_id, String theme_type) {
    	Comment searchCom = new Comment();
    	searchCom.setTheme_id(theme_id);
    	searchCom.setTheme_type(theme_type);
    	Integer num = commentImpl.getMaxFloorNum(searchCom);
    	if(num == null) {
    		num = 0;
    	}
    	return num+1;
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
		if(StringUtils.isNullOrSpace(request.getThemeType())){
			return true;
		}
		if(request.getThemeId()==null){
			return true;
		}
		if(request.getUserId()==null){
			return true;
		}
		return false;
	}
	private boolean paramterCheck(PraiseRequestBodyData request) {
		
		return false;
	}
}
