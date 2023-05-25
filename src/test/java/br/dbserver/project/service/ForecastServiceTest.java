package br.dbserver.project.service;

import br.dbserver.project.dto.forecast.ForecastRequest;
import br.dbserver.project.dto.forecast.ForecastUpdate;
import br.dbserver.project.exceptions.InvalidValueException;
import br.dbserver.project.model.City;
import br.dbserver.project.model.Forecast;
import br.dbserver.project.repository.ForecastRepository;
import br.dbserver.project.service.city.CityService;
import br.dbserver.project.service.forecast.ForecastService;
import br.dbserver.project.service.forecast.check.*;
import br.dbserver.project.stubs.CityStub;
import br.dbserver.project.stubs.ForecastStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class ForecastServiceTest {

    @InjectMocks
    private ForecastService forecastService;

    @Mock
    private ForecastRepository forecastRepository;

    @Mock
    private CityService cityService;

    @Mock
    private ForecastSystemChecker forecastSystemChecker;

    Forecast forecastDefault = ForecastStub.forecastDefault();
    City cityDefault = CityStub.cityDefault();

    @BeforeEach
    public void setup() {
        openMocks(this);
        when(forecastRepository.save(forecastDefault)).thenReturn(forecastDefault);
        when(cityService.getCityByName(forecastDefault.getCity().getName())).thenReturn(cityDefault);
        when(forecastRepository.findById(forecastDefault.getId())).thenReturn(Optional.ofNullable(forecastDefault));
    }

    @Test
    public void Should_ReturnOk_When_CreateNewForecast() throws Exception {
        ForecastRequest request = new ForecastRequest(forecastDefault);

        Field checkForecastField = forecastService.getClass().getDeclaredField("checkForecast");
        checkForecastField.setAccessible(true);
        checkForecastField.set(forecastService, Collections.singletonList(forecastSystemChecker));
        doNothing().when(forecastSystemChecker).check(any(Forecast.class));

        forecastService.saveForecast(request);
    }

    @Test
    public void Should_ThrowInvalidValueException_When_AirSpeedLessThanZero() throws Exception {
        assertThrows(InvalidValueException.class, () -> new CheckAirSpeed().check(ForecastStub.forecastWithAirSpeedLessThanZero()));
    }

    @Test
    public void Should_ThrowInvalidValueException_When_AirSpeedMoreThanThirty() throws Exception {
        assertThrows(InvalidValueException.class, () -> new CheckAirSpeed().check(ForecastStub.forecastWithAirSpeedMoreThanThirty()));
    }

    @Test
    public void Should_ThrowInvalidValueException_When_HumidityLessThanZero() throws Exception {
        assertThrows(InvalidValueException.class, () -> new CheckHumidity().check(ForecastStub.forecastWithHumidityLessThanZero()));
    }

    @Test
    public void Should_ThrowInvalidValueException_When_HumidityMoreThanAHundred() throws Exception {
        assertThrows(InvalidValueException.class, () -> new CheckHumidity().check(ForecastStub.forecastWithHumidityMoreThanAHundred()));
    }

    @Test
    public void Should_ThrowInvalidValueException_When_PrecipitationLessThanZero() throws Exception {
        assertThrows(InvalidValueException.class, () -> new CheckPrecipitation().check(ForecastStub.forecastWithPrecipitationLessThanZero()));
    }

    @Test
    public void Should_ThrowInvalidValueException_When_AirSpeedMoreThanAHundred() throws Exception {
        assertThrows(InvalidValueException.class, () -> new CheckPrecipitation().check(ForecastStub.forecastWithPrecipitationMoreThanAHundred()));
    }

    @Test
    public void Should_ThrowInvalidValueException_When_MaxTemperatureLessThanMinusFifty() throws Exception {
        assertThrows(InvalidValueException.class, () -> new CheckMaxTemperatureValue().check(ForecastStub.forecastWithTemperatureLessThanMinusFifty()));
    }

    @Test
    public void Should_ThrowInvalidValueException_When_MaxTemperatureMoreThanFifty() throws Exception {
        assertThrows(InvalidValueException.class, () -> new CheckMaxTemperatureValue().check(ForecastStub.forecastWithTemperatureMoreThanFifty()));
    }

    @Test
    public void Should_ThrowInvalidValueException_When_MinTemperatureLessThanMinusFifty() throws Exception {
        assertThrows(InvalidValueException.class, () -> new CheckMinTemperatureValue().check(ForecastStub.forecastWithTemperatureLessThanMinusFifty()));
    }

    @Test
    public void Should_ThrowInvalidValueException_When_MinTemperatureMoreThanFifty() throws Exception {
        assertThrows(InvalidValueException.class, () -> new CheckMinTemperatureValue().check(ForecastStub.forecastWithTemperatureMoreThanFifty()));
    }

    @Test
    public void Should_ThrowInvalidValueException_When_MinTemperatureIsHigherThanMaxTemperature() throws Exception {
        assertThrows(InvalidValueException.class, () -> new CheckMinAndMaxTemperature().check(ForecastStub.forecastWithMinTemperatureIsHigherThanMaxTemperature()));
    }

    @Test
    public void Should_ReturnOk_When_GettingForecastsByCity() throws Exception {
        Pageable pageable = PageRequest.of(0,10);
        Page<Forecast> forecastPage = new PageImpl<>(ForecastStub.forecastList().getBody(), pageable, ForecastStub.forecastList().getBody().size());

        when(forecastRepository.findAllByCityId(forecastDefault.getCity().getId(), pageable)).thenReturn(forecastPage);

        ResponseEntity response = forecastService.getForecastsByCity(ForecastStub.forecastDefault().getCity().getName(), pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(forecastPage, response.getBody());
    }

    @Test
    public void Should_ReturnOk_When_UpdatingForecast() throws Exception {
        ForecastUpdate request = new ForecastUpdate(forecastDefault);

        Field checkForecastField = forecastService.getClass().getDeclaredField("checkForecast");
        checkForecastField.setAccessible(true);
        checkForecastField.set(forecastService, Collections.singletonList(forecastSystemChecker));
        doNothing().when(forecastSystemChecker).check(any(Forecast.class));

        ResponseEntity response = forecastService.updateForecast(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void Should_ReturnOk_When_DeletingForecast() throws Exception {
        doNothing().when(forecastRepository).delete(forecastDefault);

        ResponseEntity response = forecastService.deleteForecast(forecastDefault.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void Should_ReturnChuvaNoite_When_SettingWeatherStatus() throws Exception {
        Forecast forecast = ForecastStub.forecastWithStatusChuvaNoite();
        forecastService.setWeatherStatus(forecast);
        assertEquals("Noite chuvosa", forecast.getWeatherStatus());
    }

    @Test
    public void Should_ReturnChuvaDia_When_SettingWeatherStatus() throws Exception {
        Forecast forecast = ForecastStub.forecastWithStatusChuvaDia();
        forecastService.setWeatherStatus(forecast);
        assertEquals("Dia chuvoso", forecast.getWeatherStatus());
    }

    @Test
    public void Should_ReturnTempestade_When_SettingWeatherStatus() throws Exception {
        Forecast forecast = ForecastStub.forecastWithStatusTempestade();
        forecastService.setWeatherStatus(forecast);
        assertEquals("Tempestade", forecast.getWeatherStatus());
    }

    @Test
    public void Should_ReturnNubladoNoite_When_SettingWeatherStatus() throws Exception {
        Forecast forecast = ForecastStub.forecastWithStatusNubladoNoite();
        forecastService.setWeatherStatus(forecast);
        assertEquals("Noite nublada", forecast.getWeatherStatus());
    }

    @Test
    public void Should_ReturnNubladoDia_When_SettingWeatherStatus() throws Exception {
        Forecast forecast = ForecastStub.forecastWithStatusNubladoDia();
        forecastService.setWeatherStatus(forecast);
        assertEquals("Dia nublado", forecast.getWeatherStatus());
    }

    @Test
    public void Should_ReturnSolComNuvens_When_SettingWeatherStatus() throws Exception {
        Forecast forecast = ForecastStub.forecastWithStatusSolComNuvens();
        forecastService.setWeatherStatus(forecast);
        assertEquals("Sol com nuvens", forecast.getWeatherStatus());
    }

    @Test
    public void Should_ReturnSol_When_SettingWeatherStatus() throws Exception {
        Forecast forecast = ForecastStub.forecastWithStatusSol();
        forecastService.setWeatherStatus(forecast);
        assertEquals("Sol", forecast.getWeatherStatus());
    }

}
