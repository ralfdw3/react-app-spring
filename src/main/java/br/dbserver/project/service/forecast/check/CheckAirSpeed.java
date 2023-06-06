package br.dbserver.project.service.forecast.check;

import br.dbserver.project.exceptions.InvalidValueException;
import br.dbserver.project.model.Forecast;
import org.springframework.stereotype.Component;

@Component
public class CheckAirSpeed implements ForecastSystemChecker {
    @Override
    public void check(Forecast forecast) {
        Integer airSpeed = forecast.getAirSpeed();

        if (airSpeed < 0 || airSpeed > 30) {
            throw new InvalidValueException("O valor da velocidade do ar precisa ser >= que 0m/s e <= a 30m/s.");
        }
    }
}
