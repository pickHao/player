package com.performer.player.customer.web.controller;

import com.performer.player.customer.web.data.LoginMsg;
import com.performer.player.customer.web.entity.ResultMsg;
import com.performer.player.customer.web.pojo.User;
import com.performer.player.customer.web.service.UserService;
import io.swagger.annotations.*;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Api(value = "用户登陆注册相关的api")
@Path("rest")
public class UserEnterController {

    @Resource
    private UserService service;
    /**
     * 用户登录
     */
    @ApiOperation(value = "用户信息",notes = "根据UserId查询信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tooken", value = "令牌", paramType = "header", dataType = "string",required = true),
            @ApiImplicitParam(name = "account", value = "账号", paramType = "form", dataType = "String",required = true),
            @ApiImplicitParam(name = "account", value = "密码", paramType = "form", dataType = "String",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "",response = LoginMsg.class)
    })
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response UserLogin(@RequestHeader String token,
                             @RequestBody String enterAcount) {

        LoginMsg msg = new LoginMsg();
        String password = "1";
        User user = service.queryUserInfoByName(enterAcount);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                return Response.status(200).entity(msg).build();
            } else{
                return Response.status(200).entity(msg).build();
            }
        }else{
            return Response.status(200).entity(msg).build();
        }

    }
}
