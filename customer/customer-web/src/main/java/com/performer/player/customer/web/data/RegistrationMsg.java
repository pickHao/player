package com.performer.player.customer.web.data;


import com.performer.player.common.utils.RestMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import lombok.Data;

@Data
@ApiModel("注册响应结果")
public class RegistrationMsg extends RestMessage {

    @ApiModelProperty(value = "注册结果")
    private String resultMsg;


}
