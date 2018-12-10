package com.performer.player.customer.web.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户信息")
@Data
public class UserVO {


    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "账号")
    private String account;
    @ApiModelProperty(value = "电话号码")
    private String telephoneNumber;
    @ApiModelProperty(value = "电子邮箱")
    private String email;
    @ApiModelProperty(value = "密码")
    private String password;
}
