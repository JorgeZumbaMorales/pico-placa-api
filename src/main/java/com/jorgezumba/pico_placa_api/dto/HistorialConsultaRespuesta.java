package com.jorgezumba.pico_placa_api.dto;

import java.time.LocalDateTime;

public record HistorialConsultaRespuesta(
        Long id,
        String placa,
        LocalDateTime fechaHora,
        boolean puedeCircular
) {}