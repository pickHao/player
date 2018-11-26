package com.performer.player.customer.web.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ResultMsg {

    @ApiModelProperty(value = "返回结果")
    private String  msg;

}
