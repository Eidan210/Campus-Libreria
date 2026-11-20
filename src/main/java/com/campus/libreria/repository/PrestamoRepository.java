package com.campus.libreria.repository;

import com.campus.libreria.entity.Libro;
import com.campus.libreria.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findBySocio_Id(Long socioId);

    List<Prestamo> findByFechaDevolucionIsNull();
}
