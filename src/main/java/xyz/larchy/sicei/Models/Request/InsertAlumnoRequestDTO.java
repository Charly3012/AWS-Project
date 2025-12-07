package xyz.larchy.sicei.Models.Request;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import xyz.larchy.sicei.Models.Alumno;

public record InsertAlumnoRequestDTO(
        @NotEmpty(message = "el campo 'nombre' no debe estar vacio")
        String nombres,

        @NotEmpty(message = "el campo 'apellido' no debe estar vacio")
        String apellidos,

        @NotEmpty(message = "el campo 'matricula' no debe estar vacio")
        String matricula,

        @NotNull(message = "el campo 'promedio' no debe ser nulo")
        @Min(value = 0, message = "El promedio no puede ser negativo")
        @Max(value = 100, message = "El promedio no pude ser mayor a 100")
        Double promedio,

        @NotEmpty(message = "el campo 'password' no debe estar vacio")
        @Length(min = 8, message = "La contraseña debe de ser minímo de 8 caracteres" )
        String password,

        String fotoPerfilUrl
) {

    public Alumno toEntity(){
        return Alumno.builder()
                .nombres(this.nombres)
                .apellidos(this.apellidos)
                .matricula(this.matricula)
                .promedio(this.promedio)
                .password(this.password)
                .fotoPerfilUrl(this.fotoPerfilUrl)
                .build();
    }
}
