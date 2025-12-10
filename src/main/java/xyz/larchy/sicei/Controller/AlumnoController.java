package xyz.larchy.sicei.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.larchy.sicei.CustomExceptions.NotFoundException;
import xyz.larchy.sicei.Models.Alumno;
import xyz.larchy.sicei.Models.Reponse.InsertAlumnoResponseDTO;
import xyz.larchy.sicei.Models.Reponse.LoginResponse;
import xyz.larchy.sicei.Models.Reponse.UploadPhotoProfileResponseDTO;
import xyz.larchy.sicei.Models.Request.*;
import xyz.larchy.sicei.Services.IAlumnoService;
import xyz.larchy.sicei.Services.ISessionService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final IAlumnoService alumnoService;
    private final ISessionService sessionService;

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
    public ResponseEntity<UploadPhotoProfileResponseDTO> uploadPhotoProfile(@PathVariable int id, @RequestParam("foto") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        try{
            String photoPerfilUrl = alumnoService.uploadPhotoProfile(id, file);
            var response = new UploadPhotoProfileResponseDTO(photoPerfilUrl);
            return ResponseEntity.ok(response);
        }catch (NotFoundException ex){
            return ResponseEntity.notFound().build();
        }catch(Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("{id}/email")
    public ResponseEntity<String> sendEmail(@PathVariable int id) {
        boolean response = alumnoService.sendEmail(id);
        if (response){
            return ResponseEntity.status(200)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"Email enviado con éxito\"}");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("{id}/session/login")
    public ResponseEntity<LoginResponse> login(@PathVariable int id, @Valid @RequestBody LoginRequestDTO request) {
        try{
            String sessionString = sessionService.login(id, request.password());
            var  response = new LoginResponse(sessionString);
            return ResponseEntity.ok(response);
        }catch (NotFoundException ex){
            return ResponseEntity.notFound().build();
        }catch(Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("{id}/session/verify")
    public ResponseEntity<String> verifyLogin(@PathVariable int id, @Valid @RequestBody VerifyLoginRequestDTO request) {
        try{
            boolean response = sessionService.verifySession(request.sessionString());
            if(response){
                return ResponseEntity.status(200)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"message\": \"Sesión verificada\"}");
            }else{
                return ResponseEntity.status(400)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\": \"Sesión invalida\"}");
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("{id}/session/logout")
    public ResponseEntity<String> logout(@PathVariable int id,@Valid @RequestBody LogoutRequestDTO request){
        try {
            boolean sessionActive= sessionService.logoutSession(request.sessionString());
            if (sessionActive){
                return ResponseEntity.status(200)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"message \": \"Session cerrada exitosamente\"}");
            }else  {
                return ResponseEntity.badRequest().build();
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}
