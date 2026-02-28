package com.jorgezumba.pico_placa_api.repository;

import com.jorgezumba.pico_placa_api.entity.HistorialConsulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialConsultaRepository
        extends JpaRepository<HistorialConsulta, Long> {
}