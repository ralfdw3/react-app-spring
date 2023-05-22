package br.dbserver.project.dto.forecast;

import br.dbserver.project.enums.Shift;
import br.dbserver.project.enums.Weather;
import br.dbserver.project.model.Forecast;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ForecastResponse(
        String city,
        Weather weather,
        Shift shift,
        BigDecimal maxTemperature,
        BigDecimal minTemperature,
        Integer precipitation,
        Integer humidity,
        Integer airSpeed,
        LocalDate date) {

    public ForecastResponse(Forecast forecast) {
        this(forecast.getCity().getName(), forecast.getWeather(), forecast.getShift(), forecast.getMaxTemperature(),
                forecast.getMinTemperature(), forecast.getPrecipitation(),
                forecast.getHumidity(), forecast.getAirSpeed(), forecast.getDate());
    }
}