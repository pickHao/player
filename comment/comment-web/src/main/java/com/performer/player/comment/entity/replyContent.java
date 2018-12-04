package com.performer.player.comment.entity;

/**
 * 回复楼层结构定义
 * @author linyunzhe
 *
 */
public class replyContent {

	/**
	 * 用户id
	 */
	public Long userId;
	/**
	 * 创建时间
	 */
	public String createTime;
	/**
     * 点赞数
     */
	public Integer numberOfPraise;
	/**
	 * 回复评论内容
	 */
	public String content;
	/**
	 * 回复的用户对象id
	 */
	public Long replyUserId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getNumberOfPraise() {
		return numberOfPraise;
	}
	public void setNumberOfPraise(Integer numberOfPraise) {
		this.numberOfPraise = numberOfPraise;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(Long replyUserId) {
		this.replyUserId = replyUserId;
	}
}
