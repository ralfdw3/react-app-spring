package br.dbserver.project.service.city;

import br.dbserver.project.exceptions.NotFoundException;
import br.dbserver.project.model.City;
import br.dbserver.project.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CityService implements CityServiceInterface {

    private final CityRepository cityRepository;

    @Override
    public City getCityByName(String cityName) {
        City city = cityRepository.findByName(cityName);

        if (city != null)
            return city;

        throw new NotFoundException("Cidade não encontrada!");
    }
}
