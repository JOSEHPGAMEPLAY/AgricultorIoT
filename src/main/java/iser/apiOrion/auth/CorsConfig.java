package iser.apiOrion.auth;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); // Permitir enviar cookies y headers de autenticación
        config.addAllowedOrigin("*"); // Permitir cualquier origen
        config.addAllowedHeader("*"); // Permitir cualquier cabecera
        config.addAllowedMethod("*"); // Permitir cualquier método (GET, POST, etc.)

        // También puedes especificar las cabeceras permitidas individualmente
        // config.addAllowedHeader("Authorization");
        // config.addAllowedHeader("Content-Type");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}