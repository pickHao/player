package com.performer.player.comment.entity;

import java.util.List;

/**
 * 获取评论返回数据结构体
 * @author linyunzhe
 *
 */
public class getCommentListResponseBodyData {

	/**
	 * 楼层数（评论id）
	 */
	public String commentId;
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
     * 用户ID
     */
	public Long userId;
    /**
     * 点赞数
     */
	public Integer numberOfPraise;
    /**
     * 回复楼中的内容
     */
	public List<replyContent> replyContent;
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
