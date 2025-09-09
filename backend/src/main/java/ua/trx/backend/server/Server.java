package ua.trx.backend.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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
    public UserServiceDB userService1(PasswordEncoder passwordEncoder) {
       return new UserGlobalDB(passwordEncoder);
    }

    @Configuration
    public class CorsConfig {
        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                            .allowedOrigins("http://26.192.100.212")
                            .allowedMethods("GET", "POST", "PUT", "DELETE");
                }
            };
        }
    }
}