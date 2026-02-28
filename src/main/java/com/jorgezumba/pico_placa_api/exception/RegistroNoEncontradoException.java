package com.jorgezumba.pico_placa_api.exception;

public class RegistroNoEncontradoException extends RuntimeException {

    public RegistroNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}