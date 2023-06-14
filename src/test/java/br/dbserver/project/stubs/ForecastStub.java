package br.dbserver.project.stubs;

import br.dbserver.project.enums.Shift;
import br.dbserver.project.enums.Weather;
import br.dbserver.project.model.Forecast;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ForecastStub {
    static Forecast forecastDefault() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("20"), new BigDecimal("10"), 10, 10, 10, LocalDate.of(2023, 6, 5));
    }

    static Forecast forecastWithAirSpeedLessThanZero() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("20"), new BigDecimal("10"), 10, 10, -10, LocalDate.now());
    }

    static Forecast forecastWithAirSpeedMoreThanThirty() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("20"), new BigDecimal("10"), 10, 10, 40, LocalDate.now());
    }

    static Forecast forecastWithHumidityLessThanZero() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("20"), new BigDecimal("10"), 10, -10, 10, LocalDate.now());
    }

    static Forecast forecastWithHumidityMoreThanAHundred() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("20"), new BigDecimal("10"), 10, 110, 10, LocalDate.now());
    }

    static Forecast forecastWithPrecipitationLessThanZero() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("20"), new BigDecimal("10"), -10, 10, 10, LocalDate.now());
    }

    static Forecast forecastWithPrecipitationMoreThanAHundred() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("20"), new BigDecimal("10"), 110, 10, 10, LocalDate.now());
    }

    static Forecast forecastWithTemperatureLessThanMinusFifty() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("-60"), new BigDecimal("-60"), 10, 10, 10, LocalDate.now());
    }

    static Forecast forecastWithTemperatureMoreThanFifty() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("60"), new BigDecimal("60"), 10, 10, 10, LocalDate.now());
    }

    static Forecast forecastWithMinTemperatureIsHigherThanMaxTemperature() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("10"), new BigDecimal("20"), 10, 10, 10, LocalDate.now());
    }

    static Forecast forecastWithStatusChuvaNoite() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.NIGHT, new BigDecimal("10"), new BigDecimal("20"), 85, 10, 10, LocalDate.now());
    }

    static Forecast forecastWithStatusChuvaDia() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("10"), new BigDecimal("20"), 85, 10, 10, LocalDate.now());
    }

    static Forecast forecastWithStatusTempestade() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.STORM, Shift.DAY, new BigDecimal("10"), new BigDecimal("20"), 85, 10, 25, LocalDate.now());
    }

    static Forecast forecastWithStatusNubladoNoite() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.NIGHT, new BigDecimal("10"), new BigDecimal("20"), 60, 70, 10, LocalDate.now());
    }

    static Forecast forecastWithStatusNubladoDia() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("10"), new BigDecimal("20"), 60, 70, 10, LocalDate.now());
    }

    static Forecast forecastWithStatusSolComNuvens() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("10"), new BigDecimal("20"), 30, 10, 10, LocalDate.now());
    }

    static Forecast forecastWithStatusSol() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.DAY, new BigDecimal("10"), new BigDecimal("20"), 5, 10, 10, LocalDate.now());
    }

    static Forecast forecastWithStatusNoite() {
        return new Forecast(1L, CityStub.cityDefault(), Weather.CLEAR, Shift.NIGHT, new BigDecimal("10"), new BigDecimal("20"), 5, 10, 10, LocalDate.now());
    }


    static ResponseEntity<List<Forecast>> forecastList() {
        List<Forecast> list = new ArrayList<>();
        list.add(forecastDefault());
        list.add(forecastDefault());
        list.add(forecastDefault());
        list.add(forecastDefault());
        list.add(forecastDefault());
        return ResponseEntity.ok(list);
    }
}
