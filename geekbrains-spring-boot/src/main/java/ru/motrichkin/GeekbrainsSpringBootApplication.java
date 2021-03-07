package ru.motrichkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.motrichkin.grpc.ProductGrpcServer;

import java.io.IOException;

@SpringBootApplication
public class GeekbrainsSpringBootApplication {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(GeekbrainsSpringBootApplication.class, args);
        context.getBean(ProductGrpcServer.class).run();
    }

}
