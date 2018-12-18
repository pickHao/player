package com.performer.player.comment.entity;

import java.util.List;

import com.performer.player.common.utils.RestMessage;

import lombok.Data;

/**
 * 获取评论返回体
 * @author linyunzhe
 *
 */
@Data
public class CommentListResponseBody extends RestMessage{

	private static final long serialVersionUID = 1L;
	private List<CommentListResponseBodyData> data;
}
