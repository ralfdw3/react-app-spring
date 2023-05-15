package br.dbserver.project.service.forecast;

import br.dbserver.project.dto.forecast.ForecastDelete;
import br.dbserver.project.dto.forecast.ForecastRequest;
import br.dbserver.project.dto.forecast.ForecastResponse;
import br.dbserver.project.dto.forecast.ForecastUpdate;
import br.dbserver.project.model.City;
import br.dbserver.project.model.Forecast;
import br.dbserver.project.repository.ForecastRepository;
import br.dbserver.project.service.city.CityService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ForecastService implements ForecastServiceInterface {
    private final ForecastRepository forecastRepository;
    private final CityService cityService;

    @Autowired
    public ForecastService(ForecastRepository forecastRepository, CityService cityService) {
        this.forecastRepository = forecastRepository;
        this.cityService = cityService;
    }

    @Transactional
    @Override
    public ResponseEntity saveForecast (ForecastRequest forecastRequest) {
        City city = cityService.getCityByName(forecastRequest.city());
        Forecast forecast = new Forecast(forecastRequest, city);
        forecastRepository.save(forecast);

        return ResponseEntity.ok(new ForecastResponse(forecast));
    }

    @Override
    public ResponseEntity getForecastsByCity (String cityName, Pageable pageable) {
        City city = cityService.getCityByName(cityName);
        Page<Forecast> forecasts = forecastRepository.findAllByCityId(city.getId(), pageable);

        return ResponseEntity.ok().body(forecasts);
    }

    @Transactional
    @Override
    public ResponseEntity updateForecast(ForecastUpdate forecastUpdate) {
        City city = cityService.getCityByName(forecastUpdate.cityName());
        Forecast forecast = forecastRepository.getReferenceById(forecastUpdate.id());
        forecast.updateForecast(forecastUpdate, city);

        forecastRepository.save(forecast);

        return ResponseEntity.ok(new ForecastResponse(forecast));
    }

    @Transactional
    @Override
    public ResponseEntity deleteForecast(Long id) {
        Forecast forecast = forecastRepository.getReferenceById(id);

        forecastRepository.delete(forecast);

        return ResponseEntity.ok(new ForecastResponse(forecast));
    }
}
