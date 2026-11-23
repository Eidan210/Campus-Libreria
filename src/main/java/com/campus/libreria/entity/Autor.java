package com.campus.libreria.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 60)
    private String nacionalidad;

    @OneToMany(mappedBy = "autor",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<Libro> libros = new ArrayList<>();

    public void agregarLibro(Libro libro){
        libros.add(libro);
        libro.asignarAutor(this);
    }

    public void quitarLibro(Libro libro){
        libros.remove(libro);
        libro.quitarAutor();
    }

    public Autor(String nombre, String nacionalidad){
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
}
