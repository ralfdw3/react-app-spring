package br.dbserver.project.service.city;

import br.dbserver.project.model.City;
import org.springframework.http.ResponseEntity;

public interface CityServiceInterface {
    City getCityByName (String city);
}
