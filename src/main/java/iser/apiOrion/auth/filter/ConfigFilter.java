package iser.apiOrion.auth.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFilter {

    /**
     * Filtro personalizado
     */
    @Autowired
    private CustomFilter customFilter;

    /**
     * Registra el filtro en la aplicacion
     * @return filtro registrado
     */
    @Bean
    public FilterRegistrationBean<CustomFilter> filterRegistrationBean() {
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(customFilter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
