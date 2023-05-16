package br.dbserver.project.service.forecast.check;

import br.dbserver.project.exceptions.InvalidValueException;
import br.dbserver.project.model.Forecast;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CheckMaxTemperatureValue implements ForecastSystemChecker{
    @Override
    public void check(Forecast forecast) {
        BigDecimal maxTemperature = forecast.getMaxTemperature();

        if (maxTemperature.compareTo(new BigDecimal("-50")) < 0 || maxTemperature.compareTo(new BigDecimal("50")) > 0){
            throw new InvalidValueException("O valor da temperatura máxima precisa ser >= -50°C e <= a +50°C.");
        }
    }
}
