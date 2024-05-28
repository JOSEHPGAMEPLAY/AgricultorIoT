package iser.apiOrion.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFilter {

    // TODO: Se pueden los @Value para crear las variables de validar los filtros


    @Bean
    public FilterRegistrationBean<CustomFilter> filterRegistrationBean() {
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();

        CustomFilter customFilter = new CustomFilter();

        registrationBean.setFilter(customFilter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
