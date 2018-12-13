package com.performer.player.customer.web.controller;

import com.customer.dao.data.User;
import com.performer.player.customer.service.UserService;
import com.performer.player.customer.web.data.LoginMsg;
import io.swagger.annotations.*;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.performer.player.common.utils.CodeConstants.LOGIN_FAILED;
import static com.performer.player.common.utils.CodeConstants.SYSTEM_OK;

@Component
@Api(description = "用户登陆注册相关的api",tags = "用于用户登陆注册相关的api")
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
            @ApiImplicitParam(name = "account", value = "账号", paramType = "form", dataType = "string",required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "form", dataType = "string",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "",response = LoginMsg.class)
    })
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response UserLogin(@ApiParam(hidden = true) @HeaderParam("token") String token,
                              @ApiParam(hidden = true) @FormParam("account") String account,
                              @ApiParam(hidden = true) @FormParam("password") String password)  {

        LoginMsg msg = new LoginMsg();

        User user = service.queryUserInfoByName(account,password);
        if (user != null) {
            msg.setIsSuccess(true);
            return Response.status(SYSTEM_OK).entity(msg).build();
        }else{
            msg.setIsSuccess(false);
            msg.setCode(LOGIN_FAILED);
            return Response.status(SYSTEM_OK).entity(msg).build();
        }

    }
}
