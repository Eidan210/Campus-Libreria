package com.campus.libreria.service;

import com.campus.libreria.dto.PrestamoResponse;
import com.campus.libreria.entity.Libro;
import com.campus.libreria.entity.Prestamo;
import com.campus.libreria.entity.Socio;
import com.campus.libreria.exception.RecursoNoEncontradoException;
import com.campus.libreria.mapper.PrestamoMapper;
import com.campus.libreria.multa.CalculadoraMulta;
import com.campus.libreria.repository.LibroRepository;
import com.campus.libreria.repository.PrestamoRepository;
import com.campus.libreria.repository.SocioRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class PrestamoService {

    private final CalculadoraMulta estandar;
    private final CalculadoraMulta estudiante;
    private final LibroRepository libroRepository;
    private final SocioRepository socioRepository;
    private final PrestamoRepository prestamoRepository;
    private final Clock clock;
    private final PrestamoMapper prestamoMapper;


    public PrestamoService(CalculadoraMulta estandar, @Qualifier("multaEstudiante") CalculadoraMulta estudiante, LibroRepository libroRepository, SocioRepository socioRepository, PrestamoRepository prestamoRepository, Clock clock,PrestamoMapper prestamoMapper) {
        this.estandar = estandar;
        this.estudiante = estudiante;
        this.libroRepository = libroRepository;
        this.socioRepository = socioRepository;
        this.prestamoRepository = prestamoRepository;
        this.clock = clock;
        this.prestamoMapper = prestamoMapper;
    }

    public long multa(int dias, boolean esEstudiante){
        return esEstudiante ? estudiante.calcular(dias) : estandar.calcular(dias);
   }

   @Transactional
   public PrestamoResponse prestar(Long libroId, Long socioId){
       Libro libro = libroRepository.findById(libroId)
               .orElseThrow(()-> new RecursoNoEncontradoException("No existe un libro con ese ID"));

       Socio socio = socioRepository.findById(socioId)
               .orElseThrow(()-> new RecursoNoEncontradoException("No existe socio con ese ID"));

       libro.marcarPrestado();

       LocalDate hoy = LocalDate.now(clock);

       Prestamo prestado = new Prestamo(libro, socio, hoy, hoy.plusDays(14));

       Prestamo guardado = prestamoRepository.save(prestado);

       return prestamoMapper.toResponse(guardado);
   }

   @Transactional
   public PrestamoResponse devolver(Long prestamoId){
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(()-> new RecursoNoEncontradoException("No se encontro prestamo con ese ID"));

        LocalDate hoy = LocalDate.now(clock);
       long diasRetraso = ChronoUnit.DAYS.between(prestamo.getFechaLimite(), hoy);
       diasRetraso = Math.max(diasRetraso, 0);

       long valorMulta = multa((int) diasRetraso, prestamo.getSocio().isEsEstudiante());

       prestamo.registrarDevolucion(hoy, valorMulta);

       prestamo.getLibro().marcarDevuelto();

       return prestamoMapper.toResponse(prestamo);
   }

   @PostConstruct
    void probarMultas(){
       log.info("Multa estándar (7 días): {}", multa(7, false));
       log.info("Multa estudiante (7 días): {}", multa(7, true));
       log.info("Multa estudiante (15 días): {}", multa(15, true));
   }
}
