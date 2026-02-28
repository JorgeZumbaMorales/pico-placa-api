package com.jorgezumba.pico_placa_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> manejarArgumentoInvalido(
            IllegalArgumentException excepcion) {

        return ResponseEntity
                .badRequest()
                .body(excepcion.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarErroresDeValidacion(
            MethodArgumentNotValidException excepcion) {

        Map<String, String> errores = new HashMap<>();

        excepcion.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errores.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return ResponseEntity
                .badRequest()
                .body(errores);
    }

    @ExceptionHandler(RegistroNoEncontradoException.class)
    public ResponseEntity<String> manejarRegistroNoEncontrado(
            RegistroNoEncontradoException excepcion) {

        return ResponseEntity
                .status(404)
                .body(excepcion.getMessage());
    }
}