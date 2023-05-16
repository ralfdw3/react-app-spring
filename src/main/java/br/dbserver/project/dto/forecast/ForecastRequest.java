package br.dbserver.project.dto.forecast;

import br.dbserver.project.enums.Shift;
import br.dbserver.project.enums.Weather;
import br.dbserver.project.model.Forecast;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ForecastRequest(
        @NotBlank
        String city,
        @NotNull
        Weather weather,
        @NotNull
        Shift shift,
        @NotNull
        BigDecimal maxTemperature,
        @NotNull
        BigDecimal minTemperature,
        @NotNull
        Integer precipitation,
        @NotNull
        Integer humidity,
        @NotNull
        Integer airSpeed) {

        public ForecastRequest(Forecast forecast) {
                this(forecast.getCity().getName(), forecast.getWeather(), forecast.getShift(), forecast.getMaxTemperature(),
                        forecast.getMinTemperature(), forecast.getPrecipitation(),
                        forecast.getHumidity(), forecast.getAirSpeed());
        }
}
