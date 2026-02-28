package com.jorgezumba.pico_placa_api.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Pattern;

public record SolicitudPicoPlaca(

        @NotBlank(message = "La placa es obligatoria")
        @Pattern(
            regexp = "^[A-Z]{3}-?\\d{4}$",
            message = "La placa debe tener formato ABC-1234"
        )
        String placa,

        @NotNull(message = "La fecha y hora son obligatorias")
        LocalDateTime fechaHora

) {}