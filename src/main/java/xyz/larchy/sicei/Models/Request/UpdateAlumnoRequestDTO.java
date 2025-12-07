package xyz.larchy.sicei.Models.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import xyz.larchy.sicei.Models.Alumno;

public record UpdateAlumnoRequestDTO(
        String nombres,
        String apellidos,
        String matricula,

        @Min(value = 0, message = "El promedio no puede ser negativo")
        @Max(value = 100, message = "El promedio no pude ser mayor a 100")
        Double promedio,

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
