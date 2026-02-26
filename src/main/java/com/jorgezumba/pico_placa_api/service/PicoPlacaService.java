package com.jorgezumba.pico_placa_api.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class PicoPlacaService {

    public boolean isAllowed(String plate, LocalDateTime dateTime) {

        validateDate(dateTime);

        int lastDigit = extractLastDigit(plate);
        DayOfWeek day = dateTime.getDayOfWeek();
        LocalTime time = dateTime.toLocalTime();

        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return true;
        }

        boolean restrictedDay = isRestrictedDay(day, lastDigit);
        boolean restrictedHour = isRestrictedHour(time);

        return !(restrictedDay && restrictedHour);
    }

    private void validateDate(LocalDateTime dateTime) {
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Date cannot be in the past");
        }
    }

    private int extractLastDigit(String plate) {
        char lastChar = plate.charAt(plate.length() - 1);

        if (!Character.isDigit(lastChar)) {
            throw new IllegalArgumentException("Plate must end with a number");
        }

        return Character.getNumericValue(lastChar);
    }

    private boolean isRestrictedDay(DayOfWeek day, int digit) {
        return switch (day) {
            case MONDAY -> digit == 1 || digit == 2;
            case TUESDAY -> digit == 3 || digit == 4;
            case WEDNESDAY -> digit == 5 || digit == 6;
            case THURSDAY -> digit == 7 || digit == 8;
            case FRIDAY -> digit == 9 || digit == 0;
            default -> false;
        };
    }

    private boolean isRestrictedHour(LocalTime time) {
        return (time.isAfter(LocalTime.of(7, 0)) && time.isBefore(LocalTime.of(9, 30))) ||
               (time.isAfter(LocalTime.of(16, 0)) && time.isBefore(LocalTime.of(19, 30)));
    }
}