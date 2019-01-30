package com.performer.player.customer.web.interceptor;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 注册拦截器
 */
@SpringBootConfiguration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Resource
    private InterceptorConfig interceptorConfig;
    @Override
    public void  addInterceptors(InterceptorRegistry registry){

        List<String> list = new ArrayList<>();
//        list.add("/login/**");

//        list.add("/static/**");
//        list.add("/index.html");
//        list.add("/");

        registry.addInterceptor(interceptorConfig).addPathPatterns("/**");

        super.addInterceptors(registry);
    }

}
