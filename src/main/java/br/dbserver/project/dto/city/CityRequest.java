package br.dbserver.project.dto.city;

import javax.validation.constraints.NotBlank;

public record CityRequest(
        @NotBlank
        String city) {
}
