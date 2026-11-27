package com.campus.libreria.controller;


import com.campus.libreria.dto.PrestamoRequest;
import com.campus.libreria.dto.PrestamoResponse;
import com.campus.libreria.service.PrestamoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/prestamos")
@RequiredArgsConstructor
@Tag(name = "Prestamos", description = "Prestar y devolver prestamos de libros")
public class PrestamoController {
    private final PrestamoService prestamoService;

    @PostMapping
    @Operation(summary = "Prestar libros a socios")
    public ResponseEntity<PrestamoResponse> prestar(@Valid @RequestBody PrestamoRequest request){
        PrestamoResponse resultado = prestamoService.prestar(request.libroId(), request.socioId());

        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resultado.idPrestamo())
                .toUri();

        return ResponseEntity.created(ubicacion).body(resultado);

    }

    @PutMapping("/{id}/devolucion")
    @Operation(summary = "Devolver libros ya prestados")
    public ResponseEntity<PrestamoResponse> devolver(@PathVariable Long id){
        PrestamoResponse devolverPrestamo = prestamoService.devolver(id);

        return ResponseEntity.ok(devolverPrestamo);
    }
}
