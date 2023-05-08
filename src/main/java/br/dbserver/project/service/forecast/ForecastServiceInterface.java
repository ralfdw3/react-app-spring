package br.dbserver.project.service.forecast;

import br.dbserver.project.model.Forecast;
import org.springframework.http.ResponseEntity;

public interface ForecastServiceInterface {

    ResponseEntity save (Forecast forecast);

}
