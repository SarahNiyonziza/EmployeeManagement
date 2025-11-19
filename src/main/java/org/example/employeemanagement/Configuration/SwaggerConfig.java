package org.example.employeemanagement.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {

    @Bean
    public OpenAPI employeeManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Management API")
                        .description("A comprehensive REST API for employee management with JWT authentication")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("API Support")
                                .email("support@company.com")));
    }
}