package com.performer.player.common.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

import static com.performer.player.common.utils.CodeMessageLibrary.codeMessageMap;

@Data
public class RestMessage implements Serializable {

    @ApiModelProperty(value = "响应编码")
    private Integer code;

    @ApiModelProperty(value = "提示信息")
    private String message;

    public void setCode(Integer code){
        this.code = code;
        setMessage(codeMessageMap.get(getCode()));
    }

}
