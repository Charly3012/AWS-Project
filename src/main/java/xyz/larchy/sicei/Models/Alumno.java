package xyz.larchy.sicei.Models;

import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Alumno {

    @NotNull(message = "el campo  'id' no debe estar vacio")
    @Min(value = 0, message = "El id no puede ser negativo")
    private int id;
    @NotEmpty(message = "el campo  'nombre' no debe estar vacio")
    private String nombres;
    @NotEmpty(message = "el campo 'apellido' no debe estar vacio")
    private String apellidos;
    @NotEmpty(message = "el campo 'matricula' no debe estar vacio")
    private String matricula;
    @NotNull(message = "el campo 'promedio' no debe ser nulo")
    @Min(value = 0, message = "El promedio no puede ser negativo")
    @Max(value = 100,message = "El promedio no pude ser mayor a 100")
    private Double promedio;
}
