package br.dbserver.project.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("API de Previsão de Tempo")
                        .description("""
                                Esta API permite criar novas previsões de tempo,
                                buscar todas as previsões de uma cidade com paginação,
                                além disso deletar e editar previsões."""));
    }
}
