package br.dbserver.project.dto.forecast;

import br.dbserver.project.enums.Shift;
import br.dbserver.project.enums.Weather;
import br.dbserver.project.model.Forecast;
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

        public ForecastUpdate(Forecast forecast) {
                this(forecast.getId(), forecast.getCity().getName(), forecast.getWeather(), forecast.getShift(), forecast.getMaxTemperature(),
                        forecast.getMinTemperature(), forecast.getPrecipitation(),
                        forecast.getHumidity(), forecast.getAirSpeed());
        }
}