package xyz.larchy.sicei.Services.Implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.larchy.sicei.Models.Alumno;
import xyz.larchy.sicei.Models.Common.ServiceResponse;
import xyz.larchy.sicei.Models.Request.InsertAlumnoRequestDTO;
import xyz.larchy.sicei.Models.Request.UpdateAlumnoRequestDTO;
import xyz.larchy.sicei.Repository.AlumnoRepository;
import xyz.larchy.sicei.Services.IAlumnoService;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlumnoService implements IAlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ArrayList<Alumno> getAlumnos() {
        return new ArrayList<Alumno>(alumnoRepository.findAll());
    }

    @Override
    public Optional<Alumno> getAlumno(int id) {
        return alumnoRepository.findById(id);
    }

    @Override
    public int insertAlumno(InsertAlumnoRequestDTO alumno) {
        var newAlumno = alumno.toEntity();
        String passHashed = passwordEncoder.encode(newAlumno.getPassword());
        newAlumno.setPassword(passHashed);

        return alumnoRepository.save(newAlumno).getId();
    }

    @Override
    public Optional<Alumno> updateAlumno (int id, UpdateAlumnoRequestDTO alumno) {
        var oldAlumno =  alumnoRepository.findById(id);

        if(oldAlumno.isEmpty()) {return Optional.empty();}
        var newAlumno = oldAlumno.get();
        var alumnoFromRequest = alumno.toEntity();

        if(alumnoFromRequest.getNombres() != null && !alumnoFromRequest.getNombres().isBlank()){
            newAlumno.setNombres(alumnoFromRequest.getNombres());
        }
        if(alumnoFromRequest.getApellidos() != null && !alumnoFromRequest.getApellidos().isBlank()){
            newAlumno.setApellidos(alumnoFromRequest.getApellidos());
        }
        if(alumnoFromRequest.getMatricula() != null && !alumnoFromRequest.getMatricula().isBlank()){
            newAlumno.setMatricula(alumnoFromRequest.getMatricula());
        }
        if(alumnoFromRequest.getPromedio() != null ){
            newAlumno.setPromedio(alumnoFromRequest.getPromedio());
        }
        if(alumnoFromRequest.getPassword() != null && !alumnoFromRequest.getPassword().isBlank()){
            newAlumno.setPassword(passwordEncoder.encode(alumnoFromRequest.getPassword()));
        }
        if(alumnoFromRequest.getFotoPerfilUrl() != null && !alumnoFromRequest.getFotoPerfilUrl().isBlank()){
            newAlumno.setFotoPerfilUrl(alumnoFromRequest.getFotoPerfilUrl());
        }

        alumnoRepository.save(newAlumno);
        return Optional.of(newAlumno);
    }

    @Override
    public boolean deleteAlumno(int id) {
        try{
            alumnoRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
