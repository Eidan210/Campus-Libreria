package com.campus.libreria.multa;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MultaEstandar implements CalculadoraMulta {

    @Override
    public long calcular(int diasRetraso) {
        return diasRetraso * 1000L;
    }
}
