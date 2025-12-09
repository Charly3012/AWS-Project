package xyz.larchy.sicei.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.larchy.sicei.Models.Alumno;
import xyz.larchy.sicei.Models.Reponse.InsertAlumnoResponseDTO;
import xyz.larchy.sicei.Models.Request.InsertAlumnoRequestDTO;
import xyz.larchy.sicei.Models.Request.UpdateAlumnoRequestDTO;
import xyz.larchy.sicei.Services.IAlumnoService;
import xyz.larchy.sicei.Services.IS3Service;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final IAlumnoService alumnoService;
    private final IS3Service s3Service;

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
        return ResponseEntity.badRequest().build();
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

    @PostMapping("{id}/fotoPerfil")
    public ResponseEntity<String> uploadPhotoProfile(@PathVariable int id, @RequestParam("file") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        try{
            alumnoService.uploadPhotoProfile(id, file);
            return ResponseEntity.ok().build();
        }catch(Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
