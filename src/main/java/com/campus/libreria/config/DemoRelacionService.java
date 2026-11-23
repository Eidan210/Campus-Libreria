package com.campus.libreria.config;

import com.campus.libreria.entity.Autor;
import com.campus.libreria.entity.Libro;
import com.campus.libreria.repository.AutorRepository;
import com.campus.libreria.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoRelacionService {

    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;

    @Transactional
    public void probar() {
        if (libroRepository.existsByIsbn("978-0-DEMO-1")) return;  // ya se probó antes

        Autor autor = new Autor("Gabriel García Márquez", "Colombiano");
        Libro l1 = new Libro("El coronel no tiene quien le escriba", "978-0-DEMO-1", 1961);
        Libro l2 = new Libro("Crónica de una muerte anunciada", "978-0-DEMO-2", 1981);

        autor.agregarLibro(l1);
        autor.agregarLibro(l2);

        autorRepository.save(autor);   // cascade ALL guarda también los libros

        autor.quitarLibro(l2);

        log.info("Libros del autor: {}", autor.getLibros().size());   // ¿qué número esperas? 1 creo
    }
}
