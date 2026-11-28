package com.campus.libreria.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
    LocalDateTime timestamp, // Fecha y hora del error
    int status,
    String error,
    String mensaje,
    String path,
    Map<String, String> errores
) {
}
