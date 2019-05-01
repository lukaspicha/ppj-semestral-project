package cz.tul;

import cz.tul.data.controllers.rest.FeedController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Main {

    @Bean
    public FeedController feedController() {
        return new FeedController();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}