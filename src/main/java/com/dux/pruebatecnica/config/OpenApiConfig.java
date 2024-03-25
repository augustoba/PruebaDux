package com.dux.pruebatecnica.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .info(new Info()
                        .title("API PRUEBA TECNICA EQUIPOS DE FUTBOL")
                        .version("0.0.1")
                        .description("Api para gestionar equipos de futbol (crear, actualizar, eliminar, listar y buscar)")
                        );
    }
}
