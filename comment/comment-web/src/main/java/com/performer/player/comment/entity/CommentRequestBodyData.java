package com.performer.player.comment.entity;

/**
 * 评论请求数据部
 * 
 * @author lin1heart
 *
 */
public class CommentRequestBodyData {
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 评论主题类型
	 */
	private String themeType;
	/**
	 * 评论的帖子、视频id
	 */
	private Long themeId;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 回复的楼层id
	 */
	private Integer replyId;
	/**
	 * 回复的用户id
	 */
	private Long replyUserId;
	/**
	 * 是否是非回复评论
	 */
	private boolean isReply;

	public boolean getIsReply() {
		return isReply;
	}

	public String getContent() {
		return content;
	}

	public String getThemeType() {
		return themeType;
	}

	public Long getThemeId() {
		return themeId;
	}

	public Long getUserId() {
		return userId;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public Long getReplyUserId() {
		return replyUserId;
	}
}
