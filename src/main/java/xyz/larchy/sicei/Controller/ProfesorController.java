package xyz.larchy.sicei.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.larchy.sicei.Models.Profesor;
import xyz.larchy.sicei.Models.Reponse.InsertProfesorResponseDTO;
import xyz.larchy.sicei.Models.Request.InsertProfesorRequestDTO;
import xyz.larchy.sicei.Models.Request.UpdateProfesorRequestDTO;
import xyz.larchy.sicei.Services.Implementation.ProfesorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profesores")
public class ProfesorController {

    private final  ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<Profesor>> getProfesores() {
        var response = profesorService.getProfesores();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getProfesor(@PathVariable int id) {
        var response = profesorService.getProfesor(id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InsertProfesorResponseDTO> insertProfesor(@Valid @RequestBody InsertProfesorRequestDTO profesor) {
        int id = profesorService.insertProfesor(profesor);
        if(id > 0) {
            var response = new InsertProfesorResponseDTO(id);
            return ResponseEntity.status(201).body(response);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> updateProfesor(@PathVariable int id, @Valid @RequestBody UpdateProfesorRequestDTO profesor) {
        var response = profesorService.updateProfesor(id, profesor);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Profesor> deleteProfesor(@PathVariable int id) {
        boolean response = profesorService.deleteProfesor(id);
        if(response){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}