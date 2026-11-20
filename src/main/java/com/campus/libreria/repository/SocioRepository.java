package com.campus.libreria.repository;

import com.campus.libreria.entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SocioRepository extends JpaRepository<Socio, Long> {

    Optional<Socio> findByEmail(String email);

    List<Socio> findByEsEstudianteTrue();
}
