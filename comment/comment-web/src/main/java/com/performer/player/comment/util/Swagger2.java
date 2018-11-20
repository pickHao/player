package com.performer.player.comment.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
/**
 * Swagger2������
 * ����spring boot����ʱ��������Application.javaͬ����Ŀ¼�¡�
 * ͨ��@Configurationע�⣬��Spring�����ظ������á�
 * ��ͨ��@EnableSwagger2ע��������Swagger2��
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    
    /**
     * ����APIӦ��
     * apiInfo() ����API�����Ϣ
     * ͨ��select()��������һ��ApiSelectorBuilderʵ��,����������Щ�ӿڱ�¶��Swagger��չ�֣�
     * ָ��ɨ��İ�·��������ָ��Ҫ����API��Ŀ¼��
     * 
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.performer.player.comment.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    
    /**
     * ������API�Ļ�����Ϣ����Щ������Ϣ��չ�����ĵ�ҳ���У�
     * ���ʵ�ַ��http://��Ŀʵ�ʵ�ַ/swagger-ui.html
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("���۽ӿ�")
                .description("������ؽӿ�")
                .termsOfServiceUrl("http://www.baidu.com")
                .contact(new Contact("name","url","email"))
                .version("1.0")
                .build();
    }
}
