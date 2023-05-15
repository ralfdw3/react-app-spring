package br.dbserver.project.dto.forecast;

import jakarta.validation.constraints.NotNull;

public record ForecastDelete(
        @NotNull
        Long id
) {
}
