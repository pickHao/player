package com.performer.player.customer.web;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collection;


/**
 * @author zzh
 * Swagger2 的配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi(){

        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.performer.player.customer.web"))
                .paths(PathSelectors.any())
                .build();

    }

    @Bean
    public Docket restConfig() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("jax-rs").apiInfo(restInfo()).forCodeGeneration(true)
                .pathMapping("/cockpit").select().paths(PathSelectors.any())// 过滤的接口
                .build().useDefaultResponseMessages(false);
    }

  // 请求url匹配，支持and or，可以过滤筛选
  /*private Predicate<String> paths() {
       return or(regex("/cockpit/.*"), regex("/rest/.*")); //
   }*/

    /**
     * springboot jersery swagger2 的整合
     * @return
     */
    private ApiInfo restInfo() {
        return new ApiInfoBuilder().title("berheley service rest api ")// 大标题
                .description("spring boot webservice 平台 API")// 小标题
                .version("2.0").build();
    }

    /**
     * springboot springmvc的整合
     * @return
     */
    private ApiInfo apiInfo(){
        //String title, String description, String version, String termsOfServiceUrl, Contact contact, String license, String licenseUrl, Collection<VendorExtension> vendorExtensions
        String title="用户信息接口";
        String description="用户信息相关接口";
        String version="1.0";
        String termsOfServiceUrl="termsOfServiceUrl";

      // String name, String url, String email
        Contact contact=new Contact("customer","www.customer.com","mail@customer.com");
        String license="license";
        String licenseUrl="licenseUrl";
        Collection<VendorExtension> vendorExtensions=new ArrayList<>();
        return new ApiInfo(title,description,version,termsOfServiceUrl,contact,license,licenseUrl,vendorExtensions);
    }
}
