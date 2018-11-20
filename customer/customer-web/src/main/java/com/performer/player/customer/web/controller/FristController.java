package com.performer.player.customer.web.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public String pageInfo(@RequestBody String userId) {
        return "welcome";
    }
}
