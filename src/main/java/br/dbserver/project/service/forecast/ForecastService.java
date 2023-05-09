package br.dbserver.project.service.forecast;

import br.dbserver.project.dto.forecast.ForecastByCity;
import br.dbserver.project.dto.forecast.ForecastRequest;
import br.dbserver.project.dto.forecast.ForecastResponse;
import br.dbserver.project.model.Forecast;
import br.dbserver.project.repository.CityRepository;
import br.dbserver.project.repository.ForecastRepository;
import br.dbserver.project.service.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForecastService implements ForecastServiceInterface {

    private final ForecastRepository forecastRepository;
    private final CityRepository cityRepository;

    @Autowired
    public ForecastService(ForecastRepository forecastRepository, CityRepository cityRepository) {
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
    }


    @Override
    public ResponseEntity save (ForecastRequest forecastRequest) {
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity getByCity (String cityName, Pageable pageable) {

        List<Forecast> forecasts = forecastRepository.findForecastsByCityId(cityRepository.findByName(cityName).getId());
        if (forecasts.isEmpty()) {
            throw new NotFoundException("Nenhuma previsão encontrada para a cidade " + forecastByCity.city().getName());
        }
        List<ForecastResponse> forecastResponses = forecasts.stream().map(city -> ).collect(Collectors.toList());
        return ResponseEntity.ok().body(new PageImpl<>(forecastResponses, pageable, forecasts.size()));

    }
}
