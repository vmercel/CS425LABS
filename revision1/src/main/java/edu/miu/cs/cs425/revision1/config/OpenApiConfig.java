package edu.miu.cs.cs425.revision1.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "eShop WebAPI",
                version = "1.0",
                description = "API documentation for eShop WebAPI"
        )
)
public class OpenApiConfig {
    // No need for @Bean Docket here; auto-configuration handles it
}