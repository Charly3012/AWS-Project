package xyz.larchy.sicei.Services;

import xyz.larchy.sicei.Models.Profesor;
import xyz.larchy.sicei.Models.Request.InsertProfesorRequestDTO;
import xyz.larchy.sicei.Models.Request.UpdateProfesorRequestDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IProfesorService {
    List<Profesor> getProfesores();
    Optional<Profesor> getProfesor(int id);
    int insertProfesor(InsertProfesorRequestDTO profesor);
    Optional<Profesor> updateProfesor(int id, UpdateProfesorRequestDTO profesor);
    boolean deleteProfesor(int id);
}
