package ru.motrichkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.motrichkin.grpc.ProductGrpcServer;
import ru.motrichkin.persistence.Product;
import ru.motrichkin.persistence.sql2o.ProductRepositorySql20Impl;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class GeekbrainsSpringBootApplication implements CommandLineRunner {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(GeekbrainsSpringBootApplication.class, args);
        context.getBean(ProductGrpcServer.class).run();
    }

    @Autowired
    private ProductRepositorySql20Impl productRepositorySql20Impl;

    @Override
    public void run(String... args) throws Exception {
        List<Product> result = productRepositorySql20Impl.findAll();
        result.forEach(System.out::println);
        System.out.println();
    }
}
