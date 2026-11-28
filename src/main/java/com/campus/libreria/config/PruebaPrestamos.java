package com.campus.libreria.config;

import com.campus.libreria.dto.PrestamoResponse;
import com.campus.libreria.entity.Libro;
import com.campus.libreria.entity.Socio;
import com.campus.libreria.exception.LibroNoDisponibleException;
import com.campus.libreria.repository.LibroRepository;
import com.campus.libreria.repository.PrestamoRepository;
import com.campus.libreria.repository.SocioRepository;
import com.campus.libreria.service.PrestamoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

@Slf4j
@Configuration
public class PruebaPrestamos {

    @Bean
    @Order(2)
    public CommandLineRunner probarPrestamos(PrestamoService service, SocioRepository socioRepo, PrestamoRepository prestamoRepo, LibroRepository libroRepo){
        return args -> {

            Page<Libro> pagina = libroRepo.findAll(PageRequest.of(1,2, Sort.by("titulo")));

            log.info("total de libros {} total de paginas {} titulos de la pagina {}", pagina.getTotalElements(), pagina.getTotalPages(), pagina.getContent().stream().map(Libro::getTitulo).toList());

            log.info("Prestamos vencidos {}", prestamoRepo.findVencidos(LocalDate.now()).size());
            if (prestamoRepo.count() > 0) { return; }
            Socio socio;
            if(socioRepo.count() == 0){
                socio = socioRepo.save(new Socio("Marian", "marian@gmail.com", true));
            }else {
                socio = socioRepo.findAll().get(0);
            }

            PrestamoResponse p = service.prestar(1L, socio.getId());
            log.info("Préstamo {} creado, vence el {}", p.idPrestamo(), p.fechaLimite());

            try {
                service.prestar(1L, socio.getId());
            }catch (LibroNoDisponibleException e) {
                log.warn("Regla funcionando: {}", e.getMessage());
            }

            PrestamoResponse devuelto = service.devolver(p.idPrestamo());
            log.info("Devuelto. Multa: {}", devuelto.multa());
        };
    }
}
