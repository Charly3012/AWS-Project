package xyz.larchy.sicei.Controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.larchy.sicei.Models.Alumno;
import xyz.larchy.sicei.Services.AlumnoService;

import java.util.ArrayList;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping(value = "ola")
    public ResponseEntity<String> HolaMundo() {
        return ResponseEntity.ok("Hola Mundo");
    }

    @GetMapping
    public ResponseEntity<ArrayList<Alumno>> getAlumnos() {
        var response =  alumnoService.getAlumnos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Alumno> getAlumno(@PathVariable int id) {
        var response = alumnoService.getAlumno(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Alumno> createAlumno(@Valid @RequestBody Alumno alumno) {
        var response = alumnoService.insertAlumno(alumno);
        return ResponseEntity.status(response.getHttpCode()).body(response.Data);
    }

    @PutMapping("{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable int id, @Valid @RequestBody Alumno alumno) {
        var response = alumnoService.updateAlumno(id, alumno);
        return ResponseEntity.status(response.getHttpCode()).body(response.Data);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Alumno> deleteAlumno(@PathVariable int id) {
        var response = alumnoService.deleteAlumno(id);
        return ResponseEntity.status(response.getHttpCode()).body(response.Data);
    }


}
