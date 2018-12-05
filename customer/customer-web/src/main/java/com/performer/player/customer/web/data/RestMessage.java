package com.performer.player.customer.web.data;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

@Data
public class RestMessage implements Serializable {

    @ApiModelProperty(value = "响应编码")
    private Integer code;

    @ApiModelProperty(value = "提示信息")
    private String message;

    public void setCode(Integer code){
        this.code = code;
//        setMessage();
    }

}
