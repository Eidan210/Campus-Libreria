package com.campus.libreria.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PrestamoRequest(
        @Positive
        @NotNull
        Long libroId,

        @Positive
        @NotNull
        Long socioId
) {
}
