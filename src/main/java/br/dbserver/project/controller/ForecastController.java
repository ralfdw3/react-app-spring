package br.dbserver.project.controller;

import br.dbserver.project.dto.forecast.ForecastByCity;
import br.dbserver.project.dto.forecast.ForecastRequest;
import br.dbserver.project.dto.forecast.ForecastResponse;
import br.dbserver.project.model.City;
import br.dbserver.project.model.Forecast;
import br.dbserver.project.service.forecast.ForecastServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1/forecast")
public class ForecastController {

    @Autowired
    private ForecastServiceInterface forecastService;

    @GetMapping
    private ResponseEntity<Page<ForecastByCity>> getForecastsByCity (@RequestParam("cityName") String cityName, @PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {

        return forecastService.getByCity(cityName, pageable);
    }

}
