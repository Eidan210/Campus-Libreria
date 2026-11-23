package com.campus.libreria.repository;

import com.campus.libreria.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByDisponibleTrue();

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByAutor_Nombre(String nombre);

    boolean existsByIsbn(String isbn);
}
