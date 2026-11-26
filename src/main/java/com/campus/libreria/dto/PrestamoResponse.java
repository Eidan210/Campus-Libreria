package com.campus.libreria.dto;

import java.time.LocalDate;

public record PrestamoResponse(
        Long idPrestamo,
        String tituloLibro,
        String nombreSocio,
        LocalDate fechaPrestamo,
        LocalDate fechaLimite,
        LocalDate fechaDevolucion,
        long multa
) {
}
