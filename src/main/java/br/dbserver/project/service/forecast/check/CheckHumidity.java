package br.dbserver.project.service.forecast.check;

import br.dbserver.project.exceptions.InvalidValueException;
import br.dbserver.project.model.Forecast;
import org.springframework.stereotype.Component;

@Component
public class CheckHumidity implements ForecastSystemChecker{
    @Override
    public void check(Forecast forecast) {
        Integer humidity = forecast.getHumidity();

        if (humidity < 0 || humidity > 100){
            throw new InvalidValueException("O valor da umidade do ar precisa ser >= que 0% e <= a 100%.");
        }
    }
}
