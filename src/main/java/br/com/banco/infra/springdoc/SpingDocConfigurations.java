package br.com.banco.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpingDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("API Extratos Bancários")
                        .description("A API representa uma camada de serviço para uma operação muito realizada" +
                                " em bancos: emissão de extrato bancário.")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("alissonjaquesrmq@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://api.extratos.bancarios/api/licenca")));
    }
}