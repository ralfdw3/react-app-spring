package br.dbserver.project.service.forecast.check;

import br.dbserver.project.exceptions.InvalidValueException;
import br.dbserver.project.model.Forecast;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CheckMinAndMaxTemperature implements ForecastSystemChecker{
    @Override
    public void check(Forecast forecast) {
        BigDecimal minTemperature = forecast.getMinTemperature();
        BigDecimal maxTemperature = forecast.getMaxTemperature();

        if (minTemperature.compareTo(maxTemperature) > 0){
            throw new InvalidValueException("O valor da temperatura mínima precisa ser menor que a temperatura máxima.");
        }
    }
}
