package com.campus.libreria.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, length = 20, unique = true)
    private String isbn;

    @Column(nullable = false)
    private int anioPublicacion;

    private boolean disponible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro(String titulo,String isbn, int anioPublicacion){
        this.titulo = titulo;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;
    }

    public void asignarAutor(Autor autor){
        if(autor == null){
            throw new IllegalArgumentException("No se ingreso un autor con que trabajar");
        }
        this.autor = autor;
    }

    public void marcarPrestado(){
        if (!disponible){
            throw new IllegalStateException("El libro no esta disponible");
        }

        this.disponible = false;
    }

    public void marcarDevuelto(){
        this.disponible = true;
    }
}
