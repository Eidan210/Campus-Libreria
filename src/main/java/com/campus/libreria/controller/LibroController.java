package com.campus.libreria.controller;

import com.campus.libreria.dto.LibroRequest;
import com.campus.libreria.dto.LibroResponse;
import com.campus.libreria.notificacion.Notificador;
import com.campus.libreria.service.LibroService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService service;
    private final Notificador sms;

    public LibroController(LibroService service, @Qualifier("smsNotificador")Notificador sms){
        this.service = service;
        this.sms = sms;
    }

    @GetMapping
    public Page<LibroResponse> listar(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size){
        return service.listar(page, size);
    }

    @GetMapping("/{id}")
    public LibroResponse buscar(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @GetMapping("/disponibles")
    public List<String> disponibles(){
        return service.titulosDisponibles();
    }

    @GetMapping("/aviso")
    public String aviso(){
        return service.recordatorio();
    }

    @GetMapping("/aviso-sms")
    public String avisoSms(){
        return sms.enviar("Tu préstamo vence mañana");
    }

    // TODO mañana: terminar POST (falta @RequestBody, la URI y .body(...))
    @PostMapping
    public ResponseEntity<LibroResponse> crear(@Valid @RequestBody LibroRequest request){
        LibroResponse resultado = service.crear(request);

        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resultado.id())
                .toUri();

        return ResponseEntity.created(ubicacion).body(resultado);
    }
}
