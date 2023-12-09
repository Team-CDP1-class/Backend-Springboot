package com.knu.cdp1.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Capstone Design Project App",
                description = "",
                version = "v1"))
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi OpenApi() {
        String[] paths = {"/api/**"};

        return GroupedOpenApi.builder()
                .group("CDP API v1")
                .pathsToMatch(paths)
                .build();
    }
}