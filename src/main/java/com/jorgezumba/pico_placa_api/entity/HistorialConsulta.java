package com.jorgezumba.pico_placa_api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HistorialConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;

    private LocalDateTime fechaHoraConsulta;

    private boolean puedeCircular;

    private LocalDateTime fechaRegistro;

    public HistorialConsulta() {}

    public HistorialConsulta(String placa,
                             LocalDateTime fechaHoraConsulta,
                             boolean puedeCircular) {
        this.placa = placa;
        this.fechaHoraConsulta = fechaHoraConsulta;
        this.puedeCircular = puedeCircular;
        this.fechaRegistro = LocalDateTime.now();
    }



    public Long getId() { return id; }

    public String getPlaca() { return placa; }

    public LocalDateTime getFechaHoraConsulta() { return fechaHoraConsulta; }

    public boolean isPuedeCircular() { return puedeCircular; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
}