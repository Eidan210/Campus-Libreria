package com.campus.libreria.dto;


public record LibroResponse(
        Long id,
        String titulo,
        String isbn,
        int anioPublicacion,
        boolean disponible,
        String nombreAutor
) {
}
