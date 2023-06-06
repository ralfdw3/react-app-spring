package br.dbserver.project.controller;

import br.dbserver.project.config.ConvertJsonString;
import br.dbserver.project.dto.forecast.ForecastRequest;
import br.dbserver.project.dto.forecast.ForecastUpdate;
import br.dbserver.project.stubs.ForecastStub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static br.dbserver.project.SqlProvider.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ForecastControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String BASE_URL = "/v1/forecast";

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {insertCity, insertForecast, insertCityForecast}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = reset)
    })
    public void Should_ReturnOk_When_GettingForecastByCity() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("cityName", "Lajeado")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].city.name").value("Lajeado"))
                .andExpect(jsonPath("$.content[0].airSpeed").value(10))
                .andExpect(jsonPath("$.content[0].humidity").value(20))
                .andExpect(jsonPath("$.content[0].maxTemperature").value(35))
                .andExpect(jsonPath("$.content[0].minTemperature").value(25))
                .andExpect(jsonPath("$.content[0].precipitation").value(20))
                .andExpect(jsonPath("$.content[0].shift").value("DAY"))
                .andExpect(jsonPath("$.content[0].weather").value("CLEAR"))
                .andExpect(jsonPath("$.content.length()").value(3));
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {insertCity, insertForecast, insertCityForecast}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = reset)
    })
    public void Should_ThrowNotFoundException_When_GettingForecastByInvalidCity() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("cityName", "ThisCityDoesntExist")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id"))
                .andExpect(status().isNotFound());
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {insertCity, insertForecast}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = reset)
    })
    public void Should_ReturnOk_When_CreatingNewForecast() throws Exception {
        ForecastRequest request = new ForecastRequest(ForecastStub.forecastDefault());

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConvertJsonString.asJsonString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {insertCity, insertForecast, insertCityForecast}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = reset)
    })
    public void Should_ReturnOk_When_UpdatingForecastById() throws Exception {
        ForecastUpdate request = new ForecastUpdate(ForecastStub.forecastDefault());

        mockMvc.perform(put(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConvertJsonString.asJsonString(request)))
                .andExpect(status().isOk());

        mockMvc.perform(get(BASE_URL)
                        .param("cityName", "Lajeado")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id"))
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].city.name").value("Lajeado"))
                .andExpect(jsonPath("$.content[0].airSpeed").value(10))
                .andExpect(jsonPath("$.content[0].humidity").value(10))
                .andExpect(jsonPath("$.content[0].maxTemperature").value(20))
                .andExpect(jsonPath("$.content[0].minTemperature").value(10))
                .andExpect(jsonPath("$.content[0].precipitation").value(10))
                .andExpect(jsonPath("$.content[0].shift").value("DAY"))
                .andExpect(jsonPath("$.content[0].weather").value("CLEAR"))
                .andExpect(status().isOk());

    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {insertCity, insertForecast, insertCityForecast}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = reset)
    })
    public void Should_ReturnOk_When_DeletingForecastById() throws Exception {
        mockMvc.perform(delete(BASE_URL)
                        .param("id", String.valueOf(1L)))
                .andExpect(status().isOk());
    }
}
