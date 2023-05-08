package br.dbserver.project.controller;

import br.dbserver.project.model.Forecast;
import br.dbserver.project.service.forecast.ForecastServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1/forecast")
public class ForecastController {

    @Autowired
    private ForecastServiceInterface forecastService;

    @GetMapping
    private ResponseEntity getWhat(){
        return ResponseEntity.ok("eh31232121122");
    }
}
