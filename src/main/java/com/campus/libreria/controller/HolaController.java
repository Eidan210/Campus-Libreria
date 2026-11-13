package com.campus.libreria.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HolaController {

    /* 3) @GetMapping: este método responde a GET /api/hola */
    //    (la ruta final es la base + esta parte: "/api" + "/hola")
    @GetMapping("/saludo")
    public String hola() {
        // 4) Lo que retornas es lo que ve el navegador
        return "Librería Campus API funcionando";
    }
}
