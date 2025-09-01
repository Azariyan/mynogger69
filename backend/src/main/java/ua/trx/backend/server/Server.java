package ua.trx.backend.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.trx.backend.Service.UserGlobalDB;
import ua.trx.backend.Service.UserServiceDB;

@SpringBootApplication
@Configuration
@EntityScan("ua.trx.backend.pidor")

public class Server {
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

    @Bean
    public UserServiceDB userService1() {
       return new UserGlobalDB();
    }
}