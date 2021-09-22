package com.kcdcb.coreapi.config;

import com.kcdcb.coreapi.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
    private static final String PRODUCT_API_NAME = "KCDCB_INTERNAL_API";
    private static final String API_VERSION = "V1";
    private static final String API_DESCRIPTION = "KCD CB API 명세서";

    @Bean
    public Docket api() {
        Parameter parameter = new ParameterBuilder()
                .name(Constants.KCDCB_REQUEST_HEADER)
                .description("Request Identify Token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List<Parameter> globalParameter = new ArrayList<>();
        globalParameter.add(parameter);

        return new Docket(DocumentationType.SWAGGER_2)
                .host("localhost:8080")
                .globalOperationParameters(globalParameter)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.kcdcb.coreapi.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(PRODUCT_API_NAME)
                .version(API_VERSION)
                .description(API_DESCRIPTION)
                .build();
    }
}