package com.performer.player.customer.web.controller;


import com.performer.player.customer.web.entity.CustomerRequestBodyData;
import com.performer.player.customer.web.entity.ResultMsg;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.coyote.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FristController {

    /**
     * 用户信息查询
     * @param userId
     * @return
     */
    @ApiOperation(value = "用户信息",notes = "根据UserId查询信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String")
    @RequestMapping(value = "user",method = RequestMethod.POST)
    public ResultMsg pageInfo(@RequestHeader String token,
                              @RequestBody CustomerRequestBodyData userParam) {

        ResultMsg msg = new ResultMsg();


        return null;

    }
}
