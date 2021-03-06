package com.performer.player.customer.web.controller;

import com.customer.dao.data.User;
import com.performer.player.customer.service.CreateTokenService;
import com.performer.player.customer.service.UserService;
import com.performer.player.customer.web.data.LoginMsg;
import com.performer.player.customer.web.data.RegistrationMsg;
import io.swagger.annotations.*;

import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Map;

import static com.performer.player.common.utils.CodeConstants.LOGIN_FAILED;
import static com.performer.player.common.utils.CodeConstants.SYSTEM_OK;
import static com.performer.player.customer.service.CreateTokenServiceImpl.USER_TOKEN_KEY;

@Component
@Api(description = "用户登陆注册相关的api",tags = "用于用户登陆注册相关的api")
@Path("")
public class UserEnterController {

    @Resource
    private UserService service;

    @Resource
    private CreateTokenService createTokenService;

    @Resource
    private RedissonClient redissonClient;

    /**
     * 用户登录
     */
    @ApiOperation(value = "用户信息",notes = "根据UserId查询信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号", paramType = "form", dataType = "string",required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "form", dataType = "string",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "",response = LoginMsg.class)
    })
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response UserLogin(@ApiParam(hidden = true) @FormParam("account") String account,
                              @ApiParam(hidden = true) @FormParam("password") String password)  {



        LoginMsg msg = new LoginMsg();

        Map userTokenMap = redissonClient.getMap(USER_TOKEN_KEY);

        User user = service.queryUserInfoByName(account,password);
        if (user != null) {
            String token = createTokenService.generateToken(account);
            userTokenMap = redissonClient.getMap(USER_TOKEN_KEY);
            msg.setIsSuccess(true);
            msg.setToken(token);
            return Response.status(SYSTEM_OK).entity(msg).build();
        }else{
            msg.setIsSuccess(false);
            msg.setCode(LOGIN_FAILED);
            return Response.status(SYSTEM_OK).entity(msg).build();
        }

    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account" ,value = "z")
    })
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "",response = RegistrationMsg.class)
    })
    @Path("register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response UserRegistration(@ApiParam(hidden = true) @FormParam("account") String acount,
                                     @ApiParam(hidden = true) @FormParam("account") String a){



        return Response.status(SYSTEM_OK).build();
    }


}
