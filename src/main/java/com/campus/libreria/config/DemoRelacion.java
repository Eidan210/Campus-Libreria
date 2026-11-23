package com.campus.libreria.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class DemoRelacion {
    @Bean
    @Order(3)
    public CommandLineRunner probarRelacion(DemoRelacionService demo){
        return args -> demo.probar();
    }
}
