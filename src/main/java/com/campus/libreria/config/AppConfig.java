package com.campus.libreria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration // es como una clase en la que hay instrucciones para crear algo (BEAN)
public class AppConfig {

    @Bean // El bean es lo que hay ahi guardalo como bean
    public Clock reloj(){
        return Clock.system(ZoneId.of("America/Bogota"));
    }
}
