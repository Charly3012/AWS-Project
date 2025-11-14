package xyz.larchy.sicei.Models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Profesor {
    @NotNull(message = "el campo 'id' no debe estar vacio")
    @Min(value = 0, message = "El id no puede ser negativo")
    private int id;

    @NotNull(message = "el campo 'numeroEmpleado' no debe estar vacio")
    @Min(value = 0, message = "El numeroEmpleado no puede ser negativo")
    private int numeroEmpleado;

    @NotEmpty(message = "el campo 'nombres' no debe estar vacio")
    private String nombres;

    @NotEmpty(message = "el campo 'apellidos' no debe estar vacio")
    private String apellidos;

    @NotNull(message = "el campo 'horasClase' no debe estar vacio")
    @Min(value = 0, message = "Las horasClase no puede ser negativo")
    private int horasClase;

}
