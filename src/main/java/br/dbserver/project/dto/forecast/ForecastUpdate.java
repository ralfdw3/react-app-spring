package br.dbserver.project.dto.forecast;

import br.dbserver.project.enums.Shift;
import br.dbserver.project.enums.Weather;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ForecastUpdate(
        @NotNull
        Long id,
        String cityName,
        Weather weather,
        Shift shift,
        BigDecimal maxTemperature,
        BigDecimal minTemperature,
        Integer precipitation,
        Integer humidity,
        Integer airSpeed) {
}
