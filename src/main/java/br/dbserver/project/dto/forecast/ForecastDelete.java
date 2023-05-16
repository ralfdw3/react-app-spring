package br.dbserver.project.dto.forecast;

import br.dbserver.project.model.Forecast;
import jakarta.validation.constraints.NotNull;

public record ForecastDelete(
        @NotNull
        Long id
) {
    public ForecastDelete(Forecast forecast) {
        this(forecast.getId());
    }
}
