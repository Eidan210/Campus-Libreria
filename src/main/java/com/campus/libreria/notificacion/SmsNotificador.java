package com.campus.libreria.notificacion;

import org.springframework.stereotype.Service;

@Service
public class SmsNotificador implements Notificador {

    @Override
    public String enviar(String mensaje){
        return "[SMS] "+ mensaje;
    }
}
