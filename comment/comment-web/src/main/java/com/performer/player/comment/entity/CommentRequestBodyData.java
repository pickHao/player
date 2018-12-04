package com.performer.player.comment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 评论请求数据部
 * @author lin1heart
 *
 */
public class CommentRequestBodyData {
	/**
	 * 评论内容
	 */
	private static final String CONTENT = "co";
	/**
	 * 评论主题类型
	 */
	private static final String THEME_TYPE = "thty";
	/**
	 * 评论的帖子、视频id
	 */
	private static final String THEME_ID = "thid";
	/**
	 * 用户id
	 */
	private static final String USER_ID = "usid";
	/**
	 * 回复的楼层id
	 */
	private static final String REPLY_ID = "reid";
	/**
	 * 回复的用户id
	 */
	private static final String REPLY_USER_ID = "reuid";
	/**
	 * 是否是非回复评论
	 */
	private static final String IS_REPLY = "isReply";

	@JsonProperty(CONTENT)
    private String content;
	
	@JsonProperty(THEME_TYPE)
	private String theme_type;
	
	@JsonProperty(THEME_ID)
	private Long theme_id;

	@JsonProperty(USER_ID)
    private Long user_id;

	@JsonProperty(REPLY_ID)
    private Integer reply_id;
	
	@JsonProperty(REPLY_USER_ID)
	private Long reply_user_id;
	
	@JsonProperty(IS_REPLY)
	private String isReply;

	public String getIsReply() {
		return isReply;
	}

	public Long getTheme_id() {
		return theme_id;
	}

	public String getTheme_type() {
		return theme_type;
	}

	public String getContent() {
		return content;
	}

	public Long getUser_id() {
		return user_id;
	}

	public Integer getReply_id() {
		return reply_id;
	}

	public Long getReply_user_id() {
		return reply_user_id;
	}
}
