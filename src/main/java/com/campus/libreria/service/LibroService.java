package com.campus.libreria.service;

import com.campus.libreria.dto.LibroResponse;
import com.campus.libreria.entity.Libro;
import com.campus.libreria.mapper.LibroMapper;
import com.campus.libreria.notificacion.Notificador;
import com.campus.libreria.repository.LibroRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class LibroService {
    // Las 4 dependencias: private (solo yo las uso) y final (llegan una vez y no cambian)
    private final LibroRepository repository;
    private final Notificador notificador;
    private final Clock reloj;
    private final LibroMapper libroMapper;

    // El constructor dice: "para existir, necesito estas 3 cosas"
    /*public LibroService(LibroRepository repository, Notificador notificador, Clock reloj){
        this.repository = repository;
        this.notificador = notificador;
        this.reloj = reloj;
    } SE BORRO POR QUE USAREMOS @RequiredArgsConstructor*/

    public List<String> titulosDisponibles(){
        return repository.findByDisponibleTrue().stream()
                .map(Libro::getTitulo)
                .toList();
    }

    public String recordatorio(){
        // LocalDate.now(reloj): "dame la fecha de HOY según ESTE reloj" (el de Bogotá)
        // Comparado con LocalDate.now() a secas, que usaría la zona del servidor
        LocalDate hoy = LocalDate.now(reloj);

        int cantidad = titulosDisponibles().size(); // cuántos títulos devuelve titulosDisponibles()

        // El service NO sabe si esto sale por email o SMS: solo usa el contrato
        return notificador.enviar("Hay "+ cantidad + " libros disponibles hoy "+ hoy);
    }

    @Transactional(readOnly = true)
    public Page<LibroResponse> listar (int pagina, int tamanio){
        Page<Libro> libros = repository.findAll(PageRequest.of(pagina,tamanio, Sort.by("titulo")));

        return libros.map(libroMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public LibroResponse buscarPorId(Long id){
        Libro libro = repository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("No existe un libro con ese ID"));

        return libroMapper.toResponse(libro);
    }

    @PostConstruct   // Spring lo llama UNA vez, apenas termina de crear el bean
    void alIniciar() {
        // Los {} se reemplazan por los valores, en orden. Es mejor que concatenar con +
        log.info("LibroService listo con {} libros", repository.count());
    }

}
