package br.dbserver.project.service.forecast.check;

import br.dbserver.project.exceptions.BadRequestException;
import br.dbserver.project.exceptions.InvalidValueException;
import br.dbserver.project.model.Forecast;
import br.dbserver.project.repository.ForecastRepository;
import br.dbserver.project.service.forecast.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckDuplicatedForecastByCityAndDate implements ForecastSystemChecker{

    private ForecastRepository forecastRepository;
    @Autowired
    public CheckDuplicatedForecastByCityAndDate(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @Override
    public void check(Forecast forecast) {
        Forecast registeredForecast = forecastRepository.findByCityAndDate(forecast.getCity(), forecast.getDate());

        if (registeredForecast != null){
            throw new BadRequestException("Já existe uma previsão cadastrada nesta data para esta cidade");
        }
    }
}
