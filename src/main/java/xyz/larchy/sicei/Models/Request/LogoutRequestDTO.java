package xyz.larchy.sicei.Models.Request;

import jakarta.validation.constraints.NotEmpty;

public record LogoutRequestDTO(
        @NotEmpty(message = "El campo 'sessionString' no debe estar vacio")
        String sessionString
) {
}
