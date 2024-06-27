package iser.apiOrion.auth;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    /**
     * Configuracion de CORS
     * @param corsConfigConstants constantes de configuracion de CORS
     * @return filtro de CORS
     */
    @Bean
    CorsFilter corsFilter(CorsConfigConstants corsConfigConstants) {
        CorsConfiguration config = corsConfigConstants.getAllCorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
