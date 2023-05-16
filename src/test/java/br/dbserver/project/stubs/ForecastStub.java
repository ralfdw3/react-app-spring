package br.dbserver.project.stubs;

import br.dbserver.project.enums.Shift;
import br.dbserver.project.enums.Weather;
import br.dbserver.project.model.Forecast;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface ForecastStub {
    static Forecast forecastDefault(){
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("20"), new BigDecimal("10"), 10, 10, 10);
    }

    static Forecast forecastWithAirSpeedLessThanZero(){
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("20"), new BigDecimal("10"), 10, 10, -10);
    }

    static Forecast forecastWithAirSpeedMoreThanThirty(){
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("20"), new BigDecimal("10"), 10, 10, 40);
    }

    static Forecast forecastWithHumidityLessThanZero(){
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("20"), new BigDecimal("10"), 10, -10, 10);
    }

    static Forecast forecastWithHumidityMoreThanAHundred(){
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("20"), new BigDecimal("10"), 10, 110, 10);
    }

    static Forecast forecastWithPrecipitationLessThanZero(){
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("20"), new BigDecimal("10"), -10, 10, 10);
    }

    static Forecast forecastWithPrecipitationMoreThanAHundred(){
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("20"), new BigDecimal("10"), 110, 10, 10);
    }

    static Forecast forecastWithTemperatureLessThanMinusFifty(){
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("-60"), new BigDecimal("-60"), 10, 10, 10);
    }

    static Forecast forecastWithTemperatureMoreThanFifty(){
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("60"), new BigDecimal("60"), 10, 10, 10);
    }

    static Forecast forecastWithMinTemperatureIsHigherThanMaxTemperature(){
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("10"), new BigDecimal("20"), 10, 10, 10);
    }

    static ResponseEntity<List<Forecast>> forecastList(){
        List<Forecast> list = new ArrayList<>();
        list.add(forecastDefault());
        list.add(forecastDefault());
        list.add(forecastDefault());
        list.add(forecastDefault());
        list.add(forecastDefault());
        return ResponseEntity.ok(list);
    }
}
