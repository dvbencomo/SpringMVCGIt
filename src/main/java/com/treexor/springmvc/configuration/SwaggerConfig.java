
package com.treexor.springmvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Date;
import java.time.LocalDateTime;


@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"om.treexor.springmvc.controller"})
public class SwaggerConfig {

	
	@Bean
    public Docket api2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Métodos sin Seguridad")
                .apiInfo(apiInfo())
                .directModelSubstitute(LocalDateTime.class, Date.class)
                .select()
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }
	
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Métodos con Seguridad")
                .apiInfo(apiInfo())
                .directModelSubstitute(LocalDateTime.class, Date.class)
                .select()
                .paths(PathSelectors.regex("/apiSecure.*"))
                .build();
    }
    
    
   
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Treexor API REST", "API REST Basada en Spring MVC - CRUD ", 
        							   "API 2.0.0", "Terms of service", 
        							   	"dvbencomo@gmail.com", 
        							   	"License of API", "https://github.com/dvbencomo/TreexorPruebaTecnica");
        return apiInfo;
    }
}