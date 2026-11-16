package com.campus.libreria.multa;

import org.springframework.stereotype.Service;

@Service
public class MultaEstudiante implements CalculadoraMulta {

    @Override
    public long calcular(int diasRetraso) {
        return Math.min(diasRetraso * 500L, 5000L);
    }
}
