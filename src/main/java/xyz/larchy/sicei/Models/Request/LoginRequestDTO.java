package xyz.larchy.sicei.Models.Request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequestDTO(
        @NotEmpty(message = "El campo 'password' no debe estar vacio")
        String password
) {
}
