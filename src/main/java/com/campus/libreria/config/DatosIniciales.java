package com.campus.libreria.config;


import com.campus.libreria.entity.Libro;
import com.campus.libreria.repository.LibroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatosIniciales {

    @Bean
    public CommandLineRunner cargarLibros(LibroRepository repository){
        return  args -> {
            if (repository.count() > 0){
                return;
            }

            repository.saveAll(List.of(
                    new Libro("Cien años de soledad", "978-0307474728", 1967),
                    new Libro("Clean Code", "978-0132350884", 2008),
                    new Libro("El tunel", "958-6656256550", 1948)
            ));
        };
    }
}
