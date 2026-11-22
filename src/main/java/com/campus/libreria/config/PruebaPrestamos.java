package com.campus.libreria.config;

import com.campus.libreria.entity.Prestamo;
import com.campus.libreria.entity.Socio;
import com.campus.libreria.repository.SocioRepository;
import com.campus.libreria.service.PrestamoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Slf4j
@Configuration
public class PruebaPrestamos {

    @Bean
    @Order(2)
    public CommandLineRunner probarPrestamos(PrestamoService service, SocioRepository socioRepo){
        return args -> {
            Socio socio;
            if(socioRepo.count() == 0){
                socio = socioRepo.save(new Socio("Marian", "marian@gmail.com", true));
            }else {
                socio = socioRepo.findAll().get(0);
            }

            Prestamo p = service.prestar(1L, socio.getId());
            log.info("Préstamo {} creado, vence el {}", p.getId(), p.getFechaLimite());

            try {
                service.prestar(1L, socio.getId());
            }catch (IllegalStateException e) {
                log.warn("Regla funcionando: {}", e.getMessage());
            }

            Prestamo devuelto = service.devolver(p.getId());
            log.info("Devuelto. Multa: {}", devuelto.getMulta());
        };
    }
}
