package br.dbserver.project.repository;

import br.dbserver.project.model.Forecast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {
    Page<Forecast> findAllByCityId(Long cityId, Pageable pageable);

}
