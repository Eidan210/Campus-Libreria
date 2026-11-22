package com.campus.libreria.config;


import com.campus.libreria.entity.Autor;
import com.campus.libreria.entity.Libro;
import com.campus.libreria.repository.AutorRepository;
import com.campus.libreria.repository.LibroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class DatosIniciales {

    @Bean
    @Order(1)
    public CommandLineRunner cargarLibros(LibroRepository repository, AutorRepository autorRepo){
        return  args -> {
            if (repository.count() == 0){
                repository.saveAll(List.of(
                        new Libro("Cien años de soledad", "978-0307474728", 1967),
                        new Libro("Clean Code", "978-0132350884", 2008),
                        new Libro("El tunel", "958-6656256550", 1948)
                ));
            }

            if (autorRepo.count() == 0) {
                // 1. AUTORES → save, uno por uno, cada uno en su variable
                Autor adriana = autorRepo.save(new Autor("Adriana", "Colombiana"));
                Autor sergio  = autorRepo.save(new Autor("Sergio", "Venezolano"));
                Autor marian  = autorRepo.save(new Autor("Marian", "Rumano"));

                // 2. Traer los libros de la BD
                List<Libro> libros = repository.findAll();

                // 3. Asignar
                libros.get(0).asignarAutor(adriana);
                libros.get(1).asignarAutor(sergio);
                libros.get(2).asignarAutor(marian);

                // 4. LIBROS → saveAll, porque ya son una lista
                repository.saveAll(libros);
            }
        };
    }
}
