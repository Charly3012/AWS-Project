package xyz.larchy.sicei.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.larchy.sicei.Models.Alumno;
import xyz.larchy.sicei.Models.Reponse.InsertAlumnoResponseDTO;
import xyz.larchy.sicei.Models.Request.InsertAlumnoRequestDTO;
import xyz.larchy.sicei.Models.Request.UpdateAlumnoRequestDTO;
import xyz.larchy.sicei.Services.IAlumnoService;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final IAlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<Alumno>> getAlumnos() {
        var response =  alumnoService.getAlumnos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Alumno> getAlumno(@PathVariable int id) {
        var response = alumnoService.getAlumno(id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InsertAlumnoResponseDTO> insertAlumno(@Valid @RequestBody InsertAlumnoRequestDTO alumno) {
        int id = alumnoService.insertAlumno(alumno);
        if(id > 0){
            var response = new InsertAlumnoResponseDTO(id);
            return ResponseEntity.status(201).body(response);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable int id, @Valid @RequestBody UpdateAlumnoRequestDTO alumno) {
        var response = alumnoService.updateAlumno(id, alumno);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Alumno> deleteAlumno(@PathVariable int id) {
        boolean response = alumnoService.deleteAlumno(id);
        if(response){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
