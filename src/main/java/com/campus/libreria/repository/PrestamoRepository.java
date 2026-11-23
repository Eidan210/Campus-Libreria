package com.campus.libreria.repository;

import com.campus.libreria.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findBySocio_Id(Long socioId);

    List<Prestamo> findByFechaDevolucionIsNull();

    @Query("SELECT p FROM Prestamo p WHERE p.fechaDevolucion IS NULL AND p.fechaLimite < :hoy")
    List<Prestamo> findVencidos(@Param("hoy")LocalDate hoy);
}
