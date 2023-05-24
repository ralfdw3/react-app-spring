package br.dbserver.project.service.forecast;

import br.dbserver.project.dto.forecast.ForecastRequest;
import br.dbserver.project.dto.forecast.ForecastResponse;
import br.dbserver.project.dto.forecast.ForecastUpdate;
import br.dbserver.project.exceptions.NotFoundException;
import br.dbserver.project.model.City;
import br.dbserver.project.model.Forecast;
import br.dbserver.project.repository.ForecastRepository;
import br.dbserver.project.service.city.CityService;
import br.dbserver.project.service.forecast.check.ForecastSystemChecker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ForecastService implements ForecastServiceInterface {
    private final ForecastRepository forecastRepository;
    private final CityService cityService;

    @Autowired
    public ForecastService(ForecastRepository forecastRepository, CityService cityService) {
        this.forecastRepository = forecastRepository;
        this.cityService = cityService;
    }

    @Autowired
    private List<ForecastSystemChecker> checkForecast;

    @Transactional
    @Override
    public ResponseEntity saveForecast(ForecastRequest forecastRequest) {
        City city = cityService.getCityByName(forecastRequest.city());
        Forecast forecast = new Forecast(forecastRequest, city);
        checkForecast.forEach(f -> f.check(forecast));
        forecastRepository.save(forecast);

        return ResponseEntity.ok(new ForecastResponse(forecast));
    }

    @Override
    public ResponseEntity getForecastsByCity(String cityName, Pageable pageable) {
        City city = cityService.getCityByName(cityName);
        Page<Forecast> forecasts = forecastRepository.findAllByCityId(city.getId(), pageable);

        return ResponseEntity.ok().body(forecasts);
    }

    @Override
    public ResponseEntity getWeekForecastsByCity(String cityName, Pageable pageable) {
        City city = cityService.getCityByName(cityName);
        Page<Forecast> forecasts = forecastRepository.findAllByCityIdAndDateGreaterThan(city.getId(), LocalDate.now(), pageable);

        return ResponseEntity.ok().body(forecasts);
    }

    @Transactional
    @Override
    public ResponseEntity updateForecast(ForecastUpdate forecastUpdate) {
        City city = cityService.getCityByName(forecastUpdate.cityName());
        Forecast forecast = getForecastById(forecastUpdate.id());
        forecast.updateForecast(forecastUpdate, city);
        checkForecast.forEach(f -> f.check(forecast));

        forecastRepository.save(forecast);

        return ResponseEntity.ok(new ForecastResponse(forecast));
    }

    @Transactional
    @Override
    public ResponseEntity deleteForecast(Long id) {
        Forecast forecast = getForecastById(id);

        forecastRepository.delete(forecast);

        return ResponseEntity.ok(new ForecastResponse(forecast));
    }

    @Override
    public ResponseEntity getForecastByCityAndDate(String cityName, LocalDate date) {
        City city = cityService.getCityByName(cityName);
        Forecast forecast = forecastRepository.findByCityAndDate(city, date);

        if (forecast != null)
            return ResponseEntity.ok(new ForecastResponse(forecast));

        throw new NotFoundException("Previsão de tempo não encontrada.");
    }

    private Forecast getForecastById(Long id) {
        return forecastRepository.findById(id).orElseThrow(() -> new NotFoundException("Previsão de tempo não encontrada."));
    }
}
