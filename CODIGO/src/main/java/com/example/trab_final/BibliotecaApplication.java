package com.example.trab_final;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.trab_final.observer.DebitoObserver;
import com.example.trab_final.service.MultaService;

@SpringBootApplication
public class BibliotecaApplication {
    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }

    @Bean
    CommandLineRunner init(MultaService multaService, DebitoObserver debitoObserver) {
        return args -> {
            multaService.registrarObserver(debitoObserver);
        };
    }
}