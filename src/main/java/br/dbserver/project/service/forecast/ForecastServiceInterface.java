package br.dbserver.project.service.forecast;

import br.dbserver.project.dto.forecast.ForecastRequest;
import br.dbserver.project.dto.forecast.ForecastUpdate;
import br.dbserver.project.model.Forecast;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface ForecastServiceInterface {

    ResponseEntity saveForecast(ForecastRequest forecastRequest);

    ResponseEntity getForecastsByCity(String cityName, Pageable pageable);

    ResponseEntity getWeekForecastsByCity(String cityName, Pageable pageable);

    ResponseEntity updateForecast(ForecastUpdate forecastUpdate);

    ResponseEntity deleteForecast(Long id);

    ResponseEntity getForecastByCityAndDate(String city, LocalDate date);

    void setWeatherStatus(Forecast forecast);
}
