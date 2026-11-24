package com.campus.libreria.mapper;


import com.campus.libreria.dto.LibroResponse;
import com.campus.libreria.entity.Libro;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibroMapper {

    public LibroResponse toResponse(Libro libro){
        return new LibroResponse(
                libro.getId(),
                libro.getTitulo(),
                libro.getIsbn(),
                libro.getAnioPublicacion(),
                libro.isDisponible(),
                libro.getAutor() != null ? libro.getAutor().getNombre() : null
        );
    }

    public List<LibroResponse> toResponseList(List<Libro> libros){
        return libros.stream()
                .map(this::toResponse)
                .toList();
    }

}
