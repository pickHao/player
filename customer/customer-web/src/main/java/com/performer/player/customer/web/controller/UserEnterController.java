package com.performer.player.customer.web.controller;


import com.performer.player.customer.web.entity.CustomerRequestBodyData;
import com.performer.player.customer.web.entity.ResultMsg;
import com.performer.player.customer.web.pojo.User;
import com.performer.player.customer.web.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.coyote.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserEnterController {

    @Resource
    private UserService service;
    /**
     * 用户登录
     */
    @ApiOperation(value = "用户信息",notes = "根据UserId查询信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String")
    @RequestMapping(value = "user",method = RequestMethod.POST)
    public ResultMsg pageInfo(@RequestHeader String token,
                              @RequestBody String enterAcount,
                              @RequestBody String password) {

        ResultMsg msg = new ResultMsg();
        User user = service.queryUserInfoByName(enterAcount);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                msg.setMsg("登录成功");
                return msg;
            } else{
                msg.setMsg("密码错误");
                return msg;
            }
        }else{
            msg.setMsg("用户不存在");
            return msg;
        }

    }
}
