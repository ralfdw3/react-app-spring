package br.dbserver.project.model;

import br.dbserver.project.enums.Shift;
import br.dbserver.project.enums.Weather;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
@AllArgsConstructor
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_city", nullable = false)
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
    private int precipitation;

    @Column(nullable = false)
    private int humidity;

    @Column(nullable = false)
    private int airSpeed;

}
