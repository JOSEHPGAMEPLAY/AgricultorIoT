package iser.apiOrion;

import iser.apiOrion.auth.serviceImpl.JwtTokenProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "iser.apiOrion")
@EnableMongoRepositories
public class ApiOrionApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        System.out.println("clave ==> "+JwtTokenProvider.passwordEncoder("123456"));
        System.out.println("tru ? ==> "+JwtTokenProvider.matchPassword("123456","$2a$12$79w.JmrXIoHFGvmicwDdzexaBBQtQKiGyUlBmrxojNBj8Xnh/cpNS"));

        SpringApplication.run(ApiOrionApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiOrionApplication.class);
    }
}
