package com.campus.libreria.notificacion;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmailNotificador implements Notificador {

    @Override
    public String enviar(String mensaje){
        return "[EMAIL] "+ mensaje;
    }
}
