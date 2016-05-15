
package com.treexor.springmvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().
        			apis(RequestHandlerSelectors.basePackage("com.treexor.springmvc.controller")).
        			paths(PathSelectors.ant("/listUsers/*")).
        			build().apiInfo(apiInfo()).useDefaultResponseMessages(false)
        			.globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder().code(500).message("500 message").
        			responseModel(new ModelRef("Error")).build(), new ResponseMessageBuilder().code(403).message("Forbidden!!!!!").
        			build()));
    }
    
   
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Treexor REST API", "REST API Basada en Spring MVC - CRUD ", "API 1.0.0", "Terms of service", "dvbencomo@gmail.com", "License of API", "API license URL");
        return apiInfo;
    }
}