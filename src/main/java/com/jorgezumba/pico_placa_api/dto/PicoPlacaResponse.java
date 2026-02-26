package com.jorgezumba.pico_placa_api.dto;

public record PicoPlacaResponse(

        String plate,
        boolean allowedToDrive,
        String message

) {}