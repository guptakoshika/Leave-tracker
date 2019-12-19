package com.example.leavetracker.Auth;

import com.example.leavetracker.constants.SwaggerConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        HashSet<String> consumesAndProduces = new HashSet<>(Arrays.asList(SwaggerConstants.CONTENT_TYPE));
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .consumes(consumesAndProduces)
                .produces(consumesAndProduces);
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(SwaggerConstants.TITLE)
                .description(SwaggerConstants.DESCRIPTION)
                .version(SwaggerConstants.VERSION)
                .build();
    }
}