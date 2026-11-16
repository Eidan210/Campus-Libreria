package com.campus.libreria.controller;

import com.campus.libreria.notificacion.Notificador;
import com.campus.libreria.service.LibroService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
