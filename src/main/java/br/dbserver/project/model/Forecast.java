package br.dbserver.project.model;

import br.dbserver.project.dto.forecast.ForecastRequest;
import br.dbserver.project.dto.forecast.ForecastUpdate;
import br.dbserver.project.enums.Shift;
import br.dbserver.project.enums.Weather;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinTable(name = "city_forecast", joinColumns = {
            @JoinColumn(name = "id_forecast", nullable = false, referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "id_city", nullable = false, referencedColumnName = "id")})
    private City city;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Weather weather;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Shift shift;

    @Column(nullable = false)
    private BigDecimal maxTemperature;

    @Column(nullable = false)
    private BigDecimal minTemperature;

    @Column(nullable = false)
    private Integer precipitation;

    @Column(nullable = false)
    private Integer humidity;

    @Column(nullable = false)
    private Integer airSpeed;

    public Forecast(ForecastRequest forecastRequest, City city) {
        this.city = city;
        this.weather = forecastRequest.weather();
        this.shift = forecastRequest.shift();
        this.maxTemperature = forecastRequest.maxTemperature();
        this.minTemperature = forecastRequest.minTemperature();
        this.precipitation = forecastRequest.precipitation();
        this.humidity = forecastRequest.humidity();
        this.airSpeed = forecastRequest.airSpeed();
    }

    public void updateForecast(ForecastUpdate forecast, City city){
        this.city = city;
        this.weather = forecast.weather();
        this.shift = forecast.shift();
        this.maxTemperature = forecast.maxTemperature();
        this.minTemperature = forecast.minTemperature();
        this.precipitation = forecast.precipitation();
        this.humidity = forecast.humidity();
        this.airSpeed = forecast.airSpeed();
    }

}
