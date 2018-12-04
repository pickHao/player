package com.performer.player.comment.entity;

import java.util.List;

/**
 * 获取评论返回数据结构体
 * @author linyunzhe
 *
 */
public class CommentListResponseBodyData {

	/**
	 * 楼层数（评论id）
	 */
	public Integer commentId;
    /**
     * 评论的主题（视频、帖子等）的id
     */
	public Long themeId;
    /**
     * 评论的主题类型
     */
	public String themeType;
    /**
     * 评论内容
     */
	public String content;
    /**
     * 创建时间
     */
	public String createTime;
    /**
     * 用户信息
     */
	public userInfo userInfo;
    /**
     * 点赞数
     */
	public Integer numberOfPraise;
    /**
     * 回复楼中的内容
     */
	public List<replyContent> replyContent;
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Long getThemeId() {
		return themeId;
	}
	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}
	public String getThemeType() {
		return themeType;
	}
	public void setThemeType(String themeType) {
		this.themeType = themeType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public userInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(userInfo userInfo) {
		this.userInfo = userInfo;
	}
	public Integer getNumberOfPraise() {
		return numberOfPraise;
	}
	public void setNumberOfPraise(Integer numberOfPraise) {
		this.numberOfPraise = numberOfPraise;
	}
	public List<replyContent> getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(List<replyContent> replyContent) {
		this.replyContent = replyContent;
	}
}
