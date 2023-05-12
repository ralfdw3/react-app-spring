package br.dbserver.project.dto.forecast;

import br.dbserver.project.enums.Shift;
import br.dbserver.project.enums.Weather;

import java.math.BigDecimal;

public record ForecastRequest(
        String city,
        Weather weather,
        Shift shift,
        BigDecimal maxTemperature,
        BigDecimal minTemperature,
        Integer precipitation,
        Integer humidity,
        Integer airSpeed) {
}
