package com.performer.player.customer.web.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录实体类
 * @author zhangzihao
 */
@ApiModel(description = "参数")
@Data
public class CustomerRequestBodyData {

	@ApiModelProperty(value = "账号")
	private String acount;
	@ApiModelProperty(value = "电话号码")
	private String telephoneNumber;
	@ApiModelProperty(value = "电子邮箱")
	private String email;
	@ApiModelProperty(value = "密码")
	private String password;

}
