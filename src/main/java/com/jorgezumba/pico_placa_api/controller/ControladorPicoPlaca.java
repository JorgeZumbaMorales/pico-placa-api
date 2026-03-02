package com.jorgezumba.pico_placa_api.controller;

import com.jorgezumba.pico_placa_api.dto.SolicitudPicoPlaca;
import com.jorgezumba.pico_placa_api.dto.HistorialConsultaRespuesta;
import com.jorgezumba.pico_placa_api.dto.RespuestaPicoPlaca;
import com.jorgezumba.pico_placa_api.service.ServicioPicoPlaca;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api/pico-placa")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorPicoPlaca {

    private final ServicioPicoPlaca servicio;

    public ControladorPicoPlaca(ServicioPicoPlaca servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/validar")
    public RespuestaPicoPlaca validar(
            @Valid @RequestBody SolicitudPicoPlaca solicitud
    ) {

        boolean puedeCircular = servicio.estaPermitido(
                solicitud.placa(),
                solicitud.fechaHora()
        );

        return new RespuestaPicoPlaca(
                solicitud.placa(),
                puedeCircular,
                puedeCircular
                        ? "El vehículo puede circular"
                        : "El vehículo no puede circular"
        );
    }

    @GetMapping("/historial")
    public Page<HistorialConsultaRespuesta> obtenerHistorial(Pageable pageable) {
        return servicio.obtenerHistorial(pageable);
    }

    @DeleteMapping("/historial/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        servicio.eliminarPorId(id);
        return ResponseEntity.noContent().build(); 
    }

    @DeleteMapping("/historial")
    public ResponseEntity<Void> eliminarTodo() {
        servicio.eliminarTodo();
        return ResponseEntity.noContent().build(); 
    }
}