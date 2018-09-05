package com.performer.player.comment.pojo;

import java.util.Date;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

@Table(name = "comment")
public class comment {

	@Column(name = "id",type = MySqlTypeConstant.INT,length = 11,isKey = true,isAutoIncrement = true)
    private Integer id;
	
	@Column(name = "content",type = MySqlTypeConstant.VARCHAR,length = 225)
    private String  content;
	
	@Column(name = "create_time",type = MySqlTypeConstant.DATETIME)
    private Date create_time;

    @Column(name = "username",type = MySqlTypeConstant.VARCHAR)
    private String username;
    
    @Column(name = "number_of_praise",type = MySqlTypeConstant.VARCHAR)
    private String number_of_praise;
    
    @Column(name = "reply_id",type = MySqlTypeConstant.VARCHAR)
    private String reply_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNumber_of_praise() {
		return number_of_praise;
	}

	public void setNumber_of_praise(String number_of_praise) {
		this.number_of_praise = number_of_praise;
	}

	public String getReply_id() {
		return reply_id;
	}

	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}
}
