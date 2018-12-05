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
	public userInfo userInfo;
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
	 * 回复的用户对象
	 */
	public userInfo replyUserInfo;
	public userInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(userInfo userInfo) {
		this.userInfo = userInfo;
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
	public userInfo getReplyUserInfo() {
		return replyUserInfo;
	}
	public void setReplyUserInfo(userInfo replyUserInfo) {
		this.replyUserInfo = replyUserInfo;
	}
}
