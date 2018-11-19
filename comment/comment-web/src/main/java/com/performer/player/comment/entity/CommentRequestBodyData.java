package com.performer.player.comment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 评论请求数据部
 * @author lin1heart
 *
 */
public class CommentRequestBodyData {
	private static final String CONTENT = "co";
	private static final String CREATE_TIME = "crti";
	private static final String USERNAME = "usna";
	private static final String REPLY_ID = "reid";

	@JsonProperty(CONTENT)
    private String content;

	@JsonProperty(CREATE_TIME)
    private String create_time;

	@JsonProperty(USERNAME)
    private String username;

	@JsonProperty(REPLY_ID)
    private String reply_id;

	public String getContent() {
		return content;
	}

	public String getCreate_time() {
		return create_time;
	}

	public String getUsername() {
		return username;
	}

	public String getReply_id() {
		return reply_id;
	}
}
