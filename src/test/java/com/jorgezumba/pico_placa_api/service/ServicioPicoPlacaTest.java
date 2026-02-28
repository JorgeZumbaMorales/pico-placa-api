package com.jorgezumba.pico_placa_api.service;

import com.jorgezumba.pico_placa_api.repository.HistorialConsultaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServicioPicoPlacaTest {

    private HistorialConsultaRepository repositorio;
    private ServicioPicoPlaca servicio;

    @BeforeEach
    void configurar() {
        repositorio = mock(HistorialConsultaRepository.class);
        servicio = new ServicioPicoPlaca(repositorio);
    }

    @Test
    void deberiaPermitirCircularFinDeSemana() {

        LocalDateTime fecha =
                LocalDateTime.of(2026, 3, 7, 8, 0); // sábado

        boolean resultado =
                servicio.estaPermitido("ABC1234", fecha);

        assertTrue(resultado);

        verify(repositorio, times(1)).save(any());
    }

    @Test
    void noDeberiaPermitirCircularLunesRestriccion() {

        LocalDateTime fecha =
                LocalDateTime.of(2026, 3, 2, 8, 0); // lunes

        boolean resultado =
                servicio.estaPermitido("ABC1231", fecha);

        assertFalse(resultado);

        verify(repositorio, times(1)).save(any());
    }

    @Test
    void deberiaLanzarExcepcionSiFechaPasada() {

        LocalDateTime fecha =
                LocalDateTime.now().minusDays(1);

        assertThrows(IllegalArgumentException.class,
                () -> servicio.estaPermitido("ABC1234", fecha));
    }
}