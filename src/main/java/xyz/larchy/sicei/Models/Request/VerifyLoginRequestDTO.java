package xyz.larchy.sicei.Models.Request;

import jakarta.validation.constraints.NotEmpty;

public record VerifyLoginRequestDTO(
        @NotEmpty(message = "El campo 'sessionString' no debe estar vacio")
        String sessionString
) {
}
