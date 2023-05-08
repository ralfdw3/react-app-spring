package br.dbserver.project.service.forecast;

import br.dbserver.project.model.Forecast;
import br.dbserver.project.repository.ForecastRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ForecastService implements ForecastServiceInterface {

    private final ForecastRepository repository;

    @Autowired
    public ForecastService(ForecastRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public ResponseEntity save (Forecast forecast) {

        return null;
    }
}
