package xyz.larchy.sicei.Models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false, length = 50, unique = true)
    private String matricula;

    @Column(nullable = false)
    private Double promedio;

    @Column(nullable = false)
    private String password;

    @Column()
    private String fotoPerfilUrl;
}
