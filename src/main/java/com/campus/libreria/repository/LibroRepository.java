package com.campus.libreria.repository;

import com.campus.libreria.entity.Libro;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibroRepository {

    // final: la lista se crea una vez y nadie la puede reemplazar por otra
    // list.of(...) Crea una lista INMUTABLE (No se le pueden agregar ni quitar libros)
    private final List<Libro> libros = List.of(
            new Libro(1L, "Cien años de soledad", "Gabriel García Márquez", true),
            new Libro(2L, "Clean Code", "Robert C. Martin", false),
            new Libro(3L, "El túnel", "Ernesto Sabato", true)
    );

    public List<Libro> findAll(){
        return libros;
    }

}
