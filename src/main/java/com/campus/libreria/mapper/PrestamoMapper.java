package com.campus.libreria.mapper;

import com.campus.libreria.dto.PrestamoResponse;
import com.campus.libreria.entity.Prestamo;
import org.springframework.stereotype.Component;

@Component
public class PrestamoMapper {

    public PrestamoResponse toResponse(Prestamo prestamo){
        return new PrestamoResponse(
                prestamo.getId(),
                prestamo.getLibro().getTitulo(),
                prestamo.getSocio().getNombre(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaLimite(),
                prestamo.getFechaDevolucion(),
                prestamo.getMulta()
        );
    }
}
