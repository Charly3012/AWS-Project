package xyz.larchy.sicei.Models.Request;

import jakarta.validation.constraints.*;
import xyz.larchy.sicei.Models.Profesor;

public record InsertProfesorRequestDTO(
        @NotNull(message = "el campo 'numeroEmpleado' no debe estar vacio")
        @Min(value = 0, message = "El numeroEmpleado no puede ser negativo")
        int numeroEmpleado,

        @NotEmpty(message = "el campo 'nombres' no debe estar vacio")
        String nombres,

        @NotEmpty(message = "el campo 'apellidos' no debe estar vacio")
        String apellidos,

        @NotNull(message = "el campo 'horasClase' no debe estar vacio")
        @Min(value = 0, message = "Las horasClase no puede ser negativo")
        int horasClase
) {
    public Profesor toEntity(){
        return Profesor.builder()
                .numeroEmpleado(this.numeroEmpleado)
                .nombres(this.nombres)
                .apellidos(this.apellidos)
                .horasClase(this.horasClase)
                .build();
    }
}
