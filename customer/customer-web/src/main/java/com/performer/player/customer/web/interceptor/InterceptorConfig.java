package com.performer.player.customer.web.interceptor;


import com.performer.player.customer.service.CreateTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
public class InterceptorConfig implements HandlerInterceptor {

    @Resource
    private CreateTokenService createTokenService;

    private String userNumber;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception{
        String token = httpServletRequest.getHeader("token");


        if(checkToken(token)){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkToken(String token) {
        userNumber = createTokenService.getUserByToken(token);
        return "".equals(userNumber) ? false : true;
    }

}
