package br.dbserver.project.dto.forecast;

import br.dbserver.project.enums.Shift;
import br.dbserver.project.enums.Weather;
import br.dbserver.project.model.City;
import jakarta.persistence.*;

import java.math.BigDecimal;

public record ForecastRequest(
        City city,
        Weather weather,
        Shift shift,
        BigDecimal maxTemperature,
        BigDecimal minTemperature,
        int precipitation,
        int humidity,
        int airSpeed) {
}
