package xyz.larchy.sicei.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.larchy.sicei.Models.Profesor;
import xyz.larchy.sicei.Services.ProfesorService;

import java.util.ArrayList;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<ArrayList<Profesor>> getProfesores() {
        var response = profesorService.getProfesores();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getProfesor(@PathVariable int id) {
        var response = profesorService.getProfesor(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Profesor> createProfesor(@Valid @RequestBody Profesor profesor) {
        var response = profesorService.insertProfesor(profesor);
        return ResponseEntity.status(response.getHttpCode()).body(response.Data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> updateProfesor(@PathVariable int id, @Valid @RequestBody Profesor profesor) {
        var response = profesorService.updateProfesor(id, profesor);
        return ResponseEntity.status(response.getHttpCode()).body(response.Data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Profesor> deleteProfesor(@PathVariable int id) {
        var response = profesorService.deleteProfesor(id);
        return ResponseEntity.status(response.getHttpCode()).body(response.Data);
    }
}