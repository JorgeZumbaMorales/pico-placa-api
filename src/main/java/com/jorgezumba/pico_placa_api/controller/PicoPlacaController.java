package com.jorgezumba.pico_placa_api.controller;

import com.jorgezumba.pico_placa_api.dto.PicoPlacaRequest;
import com.jorgezumba.pico_placa_api.dto.PicoPlacaResponse;
import com.jorgezumba.pico_placa_api.service.PicoPlacaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pico-placa")
public class PicoPlacaController {

    private final PicoPlacaService service;

    public PicoPlacaController(PicoPlacaService service) {
        this.service = service;
    }

    @PostMapping("/validate")
    public PicoPlacaResponse validate(@Valid @RequestBody PicoPlacaRequest request) {

        boolean allowed = service.isAllowed(
                request.plate(),
                request.dateTime()
        );

        return new PicoPlacaResponse(
                request.plate(),
                allowed,
                allowed ? "Vehicle can circulate" : "Vehicle cannot circulate"
        );
    }
}