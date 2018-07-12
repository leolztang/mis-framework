package com.base.framework.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Priority;

/**
 * @author leo
 */

public abstract class BaseSwaggerConfig {

    @Bean
    public Docket userApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("service-api").pathProvider(apiPathProvider())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackages()))
                .build().genericModelSubstitutes(ResponseEntity.class).useDefaultResponseMessages(false);
        return docket;
    }

    protected String basePackages() {
        return "";
    }

    protected ApiInfo apiInfo() {
        return new ApiInfoBuilder().build();
    }

    protected PathProvider apiPathProvider() {
        return new AbstractPathProvider() {

            @Override
            protected String applicationPath() {
                return "";
            }

            @Override
            protected String getDocumentationPath() {
                return "";
            }
        };
    }


}