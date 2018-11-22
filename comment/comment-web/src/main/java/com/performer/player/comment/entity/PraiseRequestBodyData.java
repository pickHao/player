package com.performer.player.comment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 点赞请求数据部
 * @author lin1heart
 *
 */
public class PraiseRequestBodyData {
	/**
	 * 评论id
	 */
	private static final String ID = "id";
	/**
	 * 用户id
	 */
	private static final String USER_ID = "usid";
	/**
	 * 被点赞的楼层id
	 */
	private static final String PRAISED_FLOOR_ID = "prfid";
	/**
	 * 被点赞的用户id
	 */
	private static final String PRAISED_USER_ID = "pruid";

	@JsonProperty(ID)
	private Long id;

	@JsonProperty(USER_ID)
    private Long user_id;

	@JsonProperty(PRAISED_FLOOR_ID)
    private Integer praised_floor_id;
	
	@JsonProperty(PRAISED_USER_ID)
	private Long praised_user_id;

	public Long getId() {
		return id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public Integer getPraised_floor_id() {
		return praised_floor_id;
	}

	public Long getPraised_user_id() {
		return praised_user_id;
	}
}
