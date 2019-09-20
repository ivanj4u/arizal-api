package com.arizal.api.util;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Component
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.arizal.api.controller"))
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(apiKey()))
                .globalOperationParameters(
                        Collections.singletonList(new ParameterBuilder()
                                .name("Authorization")
                                .description("Token Oauth 2")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .defaultValue("")
                                .build()))
                ;
    }

    @Bean
    public SecurityConfiguration security() {
        return new SecurityConfiguration(null, null, null, null, "Bearer access_token", ApiKeyVehicle.HEADER, "Authorization", ",");
    }

    private ApiKey apiKey() {
        return new ApiKey("mykey", "api_key", "header");
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Application Arizal",
                "API Arizal",
                "1.0",
                "Forza Inter",
                 new Contact("Lukman Arizal",
                        "https://www.instagram.com/lukmanarizal",
                        "lukman.arizal@gmail.com"),
                "Apache License 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList());
        return apiInfo;
    }

}
