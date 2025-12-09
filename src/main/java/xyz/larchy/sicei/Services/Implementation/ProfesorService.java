package xyz.larchy.sicei.Services.Implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.larchy.sicei.Models.Profesor;
import xyz.larchy.sicei.Models.Request.InsertProfesorRequestDTO;
import xyz.larchy.sicei.Models.Request.UpdateProfesorRequestDTO;
import xyz.larchy.sicei.Repository.ProfesorRepository;
import xyz.larchy.sicei.Services.IProfesorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfesorService implements IProfesorService {

    private final ProfesorRepository profesorRepository;

    @Override
    public List<Profesor> getProfesores() {
        return new ArrayList<Profesor>(profesorRepository.findAll());
    }

    @Override
    public Optional<Profesor> getProfesor(int id) {
        return profesorRepository.findById(id);
    }

    @Override
    public int insertProfesor(InsertProfesorRequestDTO profesor) {
        var newProfesor = profesor.toEntity();
        return profesorRepository.save(newProfesor).getId();
    }

    @Override
    public Optional<Profesor> updateProfesor(int id, UpdateProfesorRequestDTO profesor) {
        var oldProfesor = profesorRepository.findById(id);

        if (oldProfesor.isEmpty()) {
            return Optional.empty();
        }

        var newProfesor = oldProfesor.get();
        var profesorFromRequest = profesor.toEntity();

        if(profesorFromRequest.getNombres() != null && !profesorFromRequest.getNombres().isEmpty()){
            newProfesor.setNombres(profesorFromRequest.getNombres());
        }
        if(profesorFromRequest.getApellidos() != null && !profesorFromRequest.getApellidos().isEmpty()){
            newProfesor.setApellidos(profesorFromRequest.getApellidos());
        }
        if(profesorFromRequest.getHorasClase() > 0){
            newProfesor.setHorasClase(profesorFromRequest.getHorasClase());
        }
        if(profesorFromRequest.getNumeroEmpleado() > 0){
            newProfesor.setNumeroEmpleado(profesorFromRequest.getNumeroEmpleado());
        }

        profesorRepository.save(newProfesor);
        return Optional.of(newProfesor);
    }

    @Override
    public boolean deleteProfesor(int id) {
        try{
            profesorRepository.deleteById(id);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}