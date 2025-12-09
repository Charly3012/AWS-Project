package xyz.larchy.sicei.Services;

import org.springframework.web.multipart.MultipartFile;
import xyz.larchy.sicei.Models.Alumno;
import xyz.larchy.sicei.Models.Request.InsertAlumnoRequestDTO;
import xyz.larchy.sicei.Models.Request.UpdateAlumnoRequestDTO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IAlumnoService {
    List<Alumno> getAlumnos();
    Optional<Alumno> getAlumno(int id);
    int insertAlumno(InsertAlumnoRequestDTO alumno);
    Optional<Alumno> updateAlumno(int id, UpdateAlumnoRequestDTO alumno);
    boolean deleteAlumno(int id);
    boolean uploadPhotoProfile(int id, MultipartFile file) throws IOException;
    void sendEmail(int id);
}
