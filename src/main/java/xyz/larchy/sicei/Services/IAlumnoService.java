package xyz.larchy.sicei.Services;

import xyz.larchy.sicei.Models.Alumno;
import xyz.larchy.sicei.Models.Request.InsertAlumnoRequestDTO;
import xyz.larchy.sicei.Models.Request.UpdateAlumnoRequestDTO;

import java.util.List;
import java.util.Optional;

public interface IAlumnoService {
    List<Alumno> getAlumnos();
    Optional<Alumno> getAlumno(int id);
    int insertAlumno(InsertAlumnoRequestDTO alumno);
    Optional<Alumno> updateAlumno(int id, UpdateAlumnoRequestDTO alumno);
    boolean deleteAlumno(int id);
}
