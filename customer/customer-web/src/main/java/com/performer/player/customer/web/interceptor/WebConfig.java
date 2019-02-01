package com.performer.player.customer.web.interceptor;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 注册拦截器
 */
@Configuration
@Component
public class WebConfig extends WebMvcConfigurerAdapter {

    @Resource
    private InterceptorConfig interceptorConfig;

    @Override
    public void  addInterceptors(InterceptorRegistry registry){

        registry.addInterceptor(interceptorConfig).addPathPatterns("/**").excludePathPatterns("/login");

    }

}
