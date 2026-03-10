package com.cooperative.voting.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI votingOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cooperative Voting API")
                        .description("API para gerenciamento de sessões de votação cooperativa")
                        .version("v1.0.0"));
    }
}
