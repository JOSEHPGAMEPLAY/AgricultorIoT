package iser.apiOrion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ApiOrionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiOrionApplication.class, args);
    }

}
