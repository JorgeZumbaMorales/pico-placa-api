package com.jorgezumba.pico_placa_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PicoPlacaRequest(

        @NotBlank(message = "Plate is required")
        String plate,

        @NotNull(message = "Date and time is required")
        LocalDateTime dateTime

) {}