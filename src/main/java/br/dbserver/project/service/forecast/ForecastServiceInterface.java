package br.dbserver.project.service.forecast;

import br.dbserver.project.dto.forecast.ForecastDelete;
import br.dbserver.project.dto.forecast.ForecastRequest;
import br.dbserver.project.dto.forecast.ForecastUpdate;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ForecastServiceInterface {

    ResponseEntity saveForecast (ForecastRequest forecastRequest);
    ResponseEntity getForecastsByCity (String cityName, Pageable pageable);
    ResponseEntity updateForecast(ForecastUpdate forecastUpdate);
    ResponseEntity deleteForecast(Long id);
}
