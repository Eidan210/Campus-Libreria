package com.campus.libreria.repository;

import com.campus.libreria.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findBySocio_Id(Long socioId);

    List<Prestamo> findByFechaDevolucionIsNull();
}
