package br.dbserver.project.service.forecast;

import br.dbserver.project.dto.forecast.ForecastRequest;
import br.dbserver.project.dto.forecast.ForecastResponse;
import br.dbserver.project.model.City;
import br.dbserver.project.model.Forecast;
import br.dbserver.project.repository.ForecastRepository;
import br.dbserver.project.service.city.CityService;
import br.dbserver.project.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ForecastService implements ForecastServiceInterface {

    private final ForecastRepository forecastRepository;
    private final CityService cityService;

    @Transactional
    @Override
    public ResponseEntity save (ForecastRequest forecastRequest) {
        City city = cityService.getCityByName(forecastRequest.city());
        Forecast forecast = new Forecast(forecastRequest);
        forecast.setCity(city);
        forecastRepository.save(forecast);

        ForecastResponse response = new ForecastResponse(forecast);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity getByCity (String cityName, Pageable pageable) {
        City city = cityService.getCityByName(cityName);
        Page<Forecast> forecasts = forecastRepository.findAllByCityId(city.getId(), pageable);

        return ResponseEntity.ok().body(forecasts);
    }
}
