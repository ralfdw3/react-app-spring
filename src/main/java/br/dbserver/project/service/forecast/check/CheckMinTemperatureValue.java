package br.dbserver.project.service.forecast.check;

import br.dbserver.project.exceptions.InvalidValueException;
import br.dbserver.project.model.Forecast;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CheckMinTemperatureValue implements ForecastSystemChecker{
    @Override
    public void check(Forecast forecast) {
        BigDecimal minTemperature = forecast.getMinTemperature();

        if (minTemperature.compareTo(new BigDecimal("-50")) < 0 || minTemperature.compareTo(new BigDecimal("50")) > 0){
            throw new InvalidValueException("O valor da temperatura mínima precisa ser >= -50°C e <= a +50°C.");
        }
    }
}
