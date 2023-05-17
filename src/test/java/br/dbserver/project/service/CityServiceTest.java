package br.dbserver.project.service;

import br.dbserver.project.exceptions.NotFoundException;
import br.dbserver.project.model.City;
import br.dbserver.project.repository.CityRepository;
import br.dbserver.project.service.city.CityService;
import br.dbserver.project.stubs.CityStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CityServiceTest {
    @InjectMocks
    private CityService cityService;
    @Mock
    private CityRepository cityRepository;
    City cityDefault = CityStub.cityDefault();

    @BeforeEach
    public void setup() {
        openMocks(this);
        when(cityRepository.findByName(cityDefault.getName())).thenReturn(cityDefault);
    }

    @Test
    public void Should_ReturnOk_When_GettingCityByName() throws Exception {
        City response = cityService.getCityByName(cityDefault.getName());

        assertEquals(cityDefault.getName(), response.getName());
    }

    @Test
    public void Should_ThrowNotFoundException_When_GettingCityByInvalidName() throws Exception {
        assertThrows(NotFoundException.class, () -> cityService.getCityByName("Invalid city name"));
    }
}
