package com.campus.libreria.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socio_id", nullable = false)
    private Socio socio;

    @Column(nullable = false)
    private LocalDate fechaPrestamo;

    @Column(nullable = false)
    private LocalDate fechaLimite;

    @Column
    private LocalDate fechaDevolucion;

    @Column
    private long multa;

    public Prestamo(Libro libro, Socio socio, LocalDate fechaPrestamo, LocalDate fechaLimite) {
        this.libro = libro;
        this.socio = socio;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimite = fechaLimite;
    }

    public void registrarDevolucion(LocalDate fecha, long multa){
        if(fechaDevolucion != null){
            throw new IllegalStateException("Este libro ya fue devuelto");
        }

        this.fechaDevolucion = fecha;
        this.multa = multa;
    }
}
