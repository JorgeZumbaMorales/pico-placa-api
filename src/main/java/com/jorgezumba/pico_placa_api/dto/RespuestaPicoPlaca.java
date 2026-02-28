package com.jorgezumba.pico_placa_api.dto;

public record RespuestaPicoPlaca(

        String placa,
        boolean puedeCircular,
        String mensaje

) {}