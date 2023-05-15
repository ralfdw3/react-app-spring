package br.dbserver.project.service.forecast.check;

import br.dbserver.project.exceptions.InvalidValueException;
import br.dbserver.project.model.Forecast;
import org.springframework.stereotype.Component;

@Component
public class CheckPrecipitation implements ForecastSystemChecker{
    @Override
    public void check(Forecast forecast) {
        Integer precipitation = forecast.getPrecipitation();

        if (precipitation < 0 || precipitation > 100){
            throw new InvalidValueException("O valor da precipitação precisa ser >= 0 e <= a 100.");
        }
    }
}
