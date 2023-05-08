package br.dbserver.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "city_forecast", joinColumns = {
            @JoinColumn(name = "id_city", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "id_forecast", referencedColumnName = "id")
    })
    private List<Forecast> forecast;

}
