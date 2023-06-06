package br.dbserver.project.repository;

import br.dbserver.project.model.City;
import br.dbserver.project.model.Forecast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {
    Page<Forecast> findAllByCityId(Long cityId, Pageable pageable);

    Page<Forecast> findAllByCityIdAndDateGreaterThan(Long cityId, LocalDate date, Pageable pageable);

    Forecast findByCityAndDate(City city, LocalDate date);

}
