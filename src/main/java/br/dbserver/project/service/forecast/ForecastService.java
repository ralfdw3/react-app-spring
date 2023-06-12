package br.dbserver.project.service.forecast;

import br.dbserver.project.dto.forecast.ForecastRequest;
import br.dbserver.project.dto.forecast.ForecastResponse;
import br.dbserver.project.dto.forecast.ForecastUpdate;
import br.dbserver.project.enums.Shift;
import br.dbserver.project.enums.Weather;
import br.dbserver.project.exceptions.BadRequestException;
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

        if (forecastRepository.findByCityAndDate(forecast.getCity(), forecast.getDate()) != null) {
            throw new BadRequestException("Já existe uma previsão cadastrada nesta data para esta cidade");
        }
        setWeatherStatus(forecast);
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
        City city = cityService.getCityByName(forecastUpdate.city());
        Forecast forecast = getForecastById(forecastUpdate.id());
        forecast.updateForecast(forecastUpdate, city);
        checkForecast.forEach(f -> f.check(forecast));
        setWeatherStatus(forecast);
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

    @Override
    public void setWeatherStatus(Forecast forecast) {
        Shift shift = forecast.getShift();
        Weather weather = forecast.getWeather();
        Integer precipitation = forecast.getPrecipitation();
        Integer humidity = forecast.getHumidity();
        Integer airSpeed = forecast.getAirSpeed();

        if (shift == Shift.NIGHT && precipitation > 80) {
            forecast.setWeatherStatus("Noite chuvosa");
        } else if (shift == Shift.DAY && weather == Weather.CLEAR && precipitation > 80) {
            forecast.setWeatherStatus("Dia chuvoso");
        } else if (weather == Weather.STORM && precipitation > 80 && airSpeed > 15) {
            forecast.setWeatherStatus("Tempestade");
        } else if (shift == Shift.NIGHT && precipitation > 50) {
            forecast.setWeatherStatus("Noite nublada");
        } else if (shift == Shift.DAY && precipitation > 50) {
            forecast.setWeatherStatus("Dia nublado");
        } else if (shift == Shift.DAY && precipitation > 20) {
            forecast.setWeatherStatus("Sol com nuvens");
        } else if (shift == Shift.DAY && weather == Weather.CLEAR && precipitation < 10 && humidity < 30) {
            forecast.setWeatherStatus("Sol");
        } else {
            forecast.setWeatherStatus("Sol");
        }
    }

    private Forecast getForecastById(Long id) {
        return forecastRepository.findById(id).orElseThrow(() -> new NotFoundException("Previsão de tempo não encontrada."));
    }
}
