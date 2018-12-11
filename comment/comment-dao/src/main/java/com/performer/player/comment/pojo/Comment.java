package com.performer.player.comment.pojo;

public class Comment {
	
	/**
	 * id
	 */
	private Long id;
	/**
	 * 评论的id（楼层数）
	 */
    private Integer comment_id;
    /**
     * 评论的主题（视频、帖子等）的id
     */
    private Long theme_id;
    /**
     * 评论的主题类型
     */
    private String theme_type;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 创建时间
     */
    private String create_time;
    /**
     * 用户ID
     */
    private Long user_id;
    /**
     * 点赞数
     */
    private Integer number_of_praise;
    /**
     * 是否是非回复评论 
     * 0是
     * 1是回复
     */
    private boolean isReply;
    /**
     * 回复对象的楼层数
     */
    private Integer reply_id;
    /**
     * 回复对象的用户id
     */
    private Long reply_user_id;
	public boolean getIsReply() {
		return isReply;
	}
	public void setIsReply(boolean isReply) {
		this.isReply = isReply;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	public Long getTheme_id() {
		return theme_id;
	}
	public void setTheme_id(Long theme_id) {
		this.theme_id = theme_id;
	}
	public String getTheme_type() {
		return theme_type;
	}
	public void setTheme_type(String theme_type) {
		this.theme_type = theme_type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Integer getNumber_of_praise() {
		return number_of_praise;
	}
	public void setNumber_of_praise(Integer number_of_praise) {
		this.number_of_praise = number_of_praise;
	}
	public Integer getReply_id() {
		return reply_id;
	}
	public void setReply_id(Integer reply_id) {
		this.reply_id = reply_id;
	}
	public Long getReply_user_id() {
		return reply_user_id;
	}
	public void setReply_user_id(Long reply_user_id) {
		this.reply_user_id = reply_user_id;
	}
}
