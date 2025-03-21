package org.dogra.stockflow.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI authopenAPI(){
        return new OpenAPI()
                .info(new Info().title("JWT Authorication"))
                .addSecurityItem(new SecurityRequirement().addList("JWTAuthScheme"))
                .components(new Components().addSecuritySchemes("JWTAuthScheme", new SecurityScheme()
                        .name("JWTAuthScheme")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("Bearer")
                        .bearerFormat("JWT")
                ));
    }

}
