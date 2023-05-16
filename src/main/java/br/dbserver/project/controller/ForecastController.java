package br.dbserver.project.controller;

import br.dbserver.project.dto.forecast.ForecastRequest;
import br.dbserver.project.dto.forecast.ForecastResponse;
import br.dbserver.project.dto.forecast.ForecastUpdate;
import br.dbserver.project.service.forecast.ForecastServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1/forecast")
public class ForecastController {

    private ForecastServiceInterface forecastService;
    @Autowired
    public ForecastController(ForecastServiceInterface forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping
    private ResponseEntity<Page<ForecastResponse>> getForecastsByCity (@RequestParam("cityName") String cityName, @PageableDefault(size = 10, sort = {"id,desc"}) Pageable pageable) {
        return forecastService.getForecastsByCity(cityName, pageable);
    }

    @PostMapping
    private ResponseEntity saveForecast(@RequestBody @Valid ForecastRequest forecastRequest){
        return forecastService.saveForecast(forecastRequest);
    }

    @PutMapping
    private ResponseEntity updateForecast(@RequestBody @Valid ForecastUpdate forecastUpdate){
        return forecastService.updateForecast(forecastUpdate);
    }

    @DeleteMapping
    private ResponseEntity deleteForecast(@RequestParam("id") Long id){
        return forecastService.deleteForecast(id);
    }
}
