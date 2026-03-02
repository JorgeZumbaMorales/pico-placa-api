package com.jorgezumba.pico_placa_api.service;

import org.springframework.stereotype.Service;

import com.jorgezumba.pico_placa_api.dto.HistorialConsultaRespuesta;
import com.jorgezumba.pico_placa_api.entity.HistorialConsulta;
import com.jorgezumba.pico_placa_api.exception.RegistroNoEncontradoException;
import com.jorgezumba.pico_placa_api.repository.HistorialConsultaRepository;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ServicioPicoPlaca {
    private final HistorialConsultaRepository repositorio;

    public boolean estaPermitido(String placa, LocalDateTime fechaHora) {

        validarFecha(fechaHora);

        int ultimoDigito = extraerUltimoDigito(placa);
        DayOfWeek dia = fechaHora.getDayOfWeek();
        LocalTime hora = fechaHora.toLocalTime();

        if (dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY) {
            guardarHistorial(placa, fechaHora, true);
            return true;
        }

        boolean esDiaRestringido = esDiaRestringido(dia, ultimoDigito);
        boolean esHorarioRestringido = esHorarioRestringido(hora);

        boolean resultado = !(esDiaRestringido && esHorarioRestringido);

        guardarHistorial(placa, fechaHora, resultado);

        return resultado;
    }

    private void validarFecha(LocalDateTime fechaHora) {
        if (fechaHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha y hora no pueden ser anteriores a la actual.");
        }
    }

    private int extraerUltimoDigito(String placa) {
        char ultimoCaracter = placa.charAt(placa.length() - 1);

        if (!Character.isDigit(ultimoCaracter)) {
            throw new IllegalArgumentException("La placa debe terminar en un número");
        }

        return Character.getNumericValue(ultimoCaracter);
    }

    private boolean esDiaRestringido(DayOfWeek dia, int digito) {
        return switch (dia) {
            case MONDAY -> digito == 1 || digito == 2;
            case TUESDAY -> digito == 3 || digito == 4;
            case WEDNESDAY -> digito == 5 || digito == 6;
            case THURSDAY -> digito == 7 || digito == 8;
            case FRIDAY -> digito == 9 || digito == 0;
            default -> false;
        };
    }

    private boolean esHorarioRestringido(LocalTime hora) {

        LocalTime inicioManana = LocalTime.of(6, 0);
        LocalTime finManana = LocalTime.of(9, 30);

        LocalTime inicioTarde = LocalTime.of(16, 0);
        LocalTime finTarde = LocalTime.of(20, 0);

        boolean enManana = !hora.isBefore(inicioManana) && !hora.isAfter(finManana);
        boolean enTarde = !hora.isBefore(inicioTarde) && !hora.isAfter(finTarde);

        return enManana || enTarde;
    }
    
    public ServicioPicoPlaca(HistorialConsultaRepository repositorio) {
        this.repositorio = repositorio;
    }

    private void guardarHistorial(String placa,
                               LocalDateTime fechaHora,
                               boolean resultado) {

    HistorialConsulta historial =
            new HistorialConsulta(placa, fechaHora, resultado);

    repositorio.save(historial);
    }

    public Page<HistorialConsultaRespuesta> obtenerHistorial(Pageable pageable) {

    return repositorio.findAll(pageable)
            .map(historial -> new HistorialConsultaRespuesta(
                    historial.getId(),
                    historial.getPlaca(),
                    historial.getFechaHoraConsulta(),
                    historial.isPuedeCircular()
            ));
}

    public void eliminarPorId(Long id) {
        if (!repositorio.existsById(id)) {
            throw new RegistroNoEncontradoException("El registro no existe");
        }
        repositorio.deleteById(id);
    }

    public void eliminarTodo() {
        repositorio.deleteAll();
    }
}