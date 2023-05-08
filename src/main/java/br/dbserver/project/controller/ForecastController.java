package br.dbserver.project.controller;

import br.dbserver.project.model.Forecast;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1/forecast")
public class ForecastController {

    @GetMapping
    private String getWhat(){
        return "teste23";
    }
}
