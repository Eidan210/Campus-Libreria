package com.campus.libreria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public record LibroRequest(
        @NotBlank
        @Size(max = 150)
        String titulo,

        @NotBlank
        @Size(max = 20)
        String isbn,

        @Positive
        @NotNull
        Integer anioPublicacion,

        @NotNull
        @Positive
        Long autorId
) {
}
