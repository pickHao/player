package com.performer.player.comment.pojo;

public class Comment {

    private Long id;

    private String content;

    private String create_time;

    private String username;

    private int number_of_praise;

    private String reply_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getNumber_of_praise() {
		return number_of_praise;
	}

	public void setNumber_of_praise(int number_of_praise) {
		this.number_of_praise = number_of_praise;
	}

	public String getReply_id() {
		return reply_id;
	}

	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}
}
