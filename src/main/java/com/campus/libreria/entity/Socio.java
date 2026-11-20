package com.campus.libreria.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    private boolean esEstudiante;

    public Socio(String nombre, String email, boolean esEstudiante){
        this.nombre = nombre;
        this.email = email;
        this.esEstudiante = esEstudiante;
    }
}
