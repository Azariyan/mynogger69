package ua.trx.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ua.trx.backend.Console.Console;
import ua.trx.backend.Service.UserGlobalDB;
import ua.trx.backend.Service.UserServiceDB;

@SpringBootApplication
@EntityScan("ua.trx.backend.pidor")
@Configuration

@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "ua.trx.backend.server.*"))

public class apptry {
    public static void main(String[] args) {
        new SpringApplicationBuilder(apptry.class).web(WebApplicationType.NONE).run(args);
    }

    @Bean
    public CommandLineRunner runner(Console aue) {
        return args -> aue.all();
    }

    @Bean
    public Console console() {
        return new Console();
    }

    @Bean
    public UserServiceDB userService() {
        return new UserGlobalDB();
    }
}
