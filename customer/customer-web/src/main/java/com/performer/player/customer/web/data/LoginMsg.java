package com.performer.player.customer.web.data;

import com.performer.player.common.utils.RestMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "登录响应结果")
@Data
public class LoginMsg extends RestMessage {

    @ApiModelProperty(value = "登录结果")
    private Boolean isSuccess;

}
