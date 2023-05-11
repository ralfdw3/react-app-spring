package br.dbserver.project.service.forecast;

import br.dbserver.project.dto.forecast.ForecastRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ForecastServiceInterface {

    ResponseEntity save (ForecastRequest forecastRequest);
    ResponseEntity getByCity (String cityName, Pageable pageable);
}
