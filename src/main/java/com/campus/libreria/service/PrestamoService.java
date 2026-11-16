package com.campus.libreria.service;

import com.campus.libreria.multa.CalculadoraMulta;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PrestamoService {

    private final CalculadoraMulta estandar;
    private final CalculadoraMulta estudiante;

   public PrestamoService(CalculadoraMulta estandar, @Qualifier("multaEstudiante") CalculadoraMulta estudiante){
       this.estandar = estandar;
       this.estudiante = estudiante;
   }

   public long multa(int dias, boolean esEstudiante){
        return esEstudiante ? estudiante.calcular(dias) : estandar.calcular(dias);
   }

   @PostConstruct
    void probarMultas(){
       log.info("Multa estándar (7 días): {}", multa(7, false));
       log.info("Multa estudiante (7 días): {}", multa(7, true));
       log.info("Multa estudiante (15 días): {}", multa(15, true));
   }
}
