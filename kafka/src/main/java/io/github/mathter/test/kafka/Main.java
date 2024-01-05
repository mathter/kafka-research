package io.github.mathter.test.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class Main {
    public static void main(String[] args) {
        new SpringApplication(Main.class).run(args);
    }
}
