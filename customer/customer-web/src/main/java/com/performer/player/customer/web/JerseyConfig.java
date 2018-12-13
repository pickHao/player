package com.performer.player.customer.web;


import com.performer.player.customer.web.controller.UserEnterController;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.AcceptHeaderApiListingResource;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;

@Component
@Configuration
@ComponentScan({"com.performer.player.customer.web.controller"})
public class JerseyConfig extends ResourceConfig {


    public JerseyConfig(){

        //注册你的请求接口
        this.register(UserEnterController.class);

    }
    @PostConstruct
    public void init() {
        this.configureSwagger();
    }

    private void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        this.register(AcceptHeaderApiListingResource.class);
        BeanConfig config = new BeanConfig();
        config.setConfigId("user");
        config.setTitle("用户登录注册接口");
        config.setVersion("v1");
        config.setContact("player");
        config.setSchemes(new String[] { "http", "https" });
        config.setBasePath("/customer");
        config.setResourcePackage("com.performer.player.customer.web.controller");
        config.setPrettyPrint(true);
        config.setScan(true);
    }

}
