package br.dbserver.project.service.forecast;

import br.dbserver.project.dto.forecast.ForecastRequest;
import br.dbserver.project.model.City;
import br.dbserver.project.model.Forecast;
import br.dbserver.project.repository.ForecastRepository;
import br.dbserver.project.service.city.CityService;
import br.dbserver.project.service.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ForecastService implements ForecastServiceInterface {

    private final ForecastRepository forecastRepository;
    private final CityService cityService;



    @Override
    public ResponseEntity save (ForecastRequest forecastRequest) {
        City city = cityService.getCityByName(forecastRequest.city());
        Forecast forecast = new Forecast(forecastRequest);
        forecast.setCity(city);
        Forecast savedForecast = forecastRepository.save(forecast);
        return ResponseEntity.ok(savedForecast);
    }

    @Transactional
    @Override
    public ResponseEntity getByCity (String cityName, Pageable pageable) {
        City city = cityService.getCityByName(cityName);

        List<Forecast> forecasts = forecastRepository.findForecastsByCityId(city.getId());
        if (forecasts.isEmpty()) {
            throw new NotFoundException("Nenhuma previsão encontrada para a cidade " + cityName);
        }

        return ResponseEntity.ok().body(new PageImpl<>(forecasts, pageable, forecasts.size()));

    }
}
