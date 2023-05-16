package br.dbserver.project.dto.city;

import jakarta.validation.constraints.NotBlank;

public record CityRequest(
        @NotBlank
        String city) {
}
