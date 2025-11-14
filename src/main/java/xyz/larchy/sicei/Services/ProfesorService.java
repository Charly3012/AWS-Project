package xyz.larchy.sicei.Services;

import org.springframework.stereotype.Service;
import xyz.larchy.sicei.Models.Common.ServiceResponse;
import xyz.larchy.sicei.Models.Profesor;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ProfesorService {

    private ArrayList<Profesor> profesores;

    public ProfesorService() {
        this.profesores = new ArrayList<>(Arrays.asList(
                new Profesor(1, 1001, "Juan", "Pérez", 20),
                new Profesor(2, 1002, "Ana", "García", 18),
                new Profesor(3, 1003, "Luis", "Martínez", 22)
        ));
    }

    public ArrayList<Profesor> getProfesores() {
        return this.profesores;
    }

    public Profesor getProfesor(int id) {
        for (Profesor profesor : profesores) {
            if (profesor.getId() == id) {
                return profesor;
            }
        }
        return null;
    }

    public ServiceResponse<Profesor> insertProfesor(Profesor profesor) {
        for (Profesor p : profesores) {
            if (p.getId() == profesor.getId()) {
                return ServiceResponse.BadRequest(null);
            }
        }

        profesores.add(profesor);
        return ServiceResponse.CreateSuccess(profesor);
    }

    public ServiceResponse<Profesor> updateProfesor(int id, Profesor profesorNuevosDatos) {
        for (int i = 0; i < profesores.size(); i++) {
            if (profesores.get(i).getId() == id) {
                profesores.set(i, profesorNuevosDatos);
                return ServiceResponse.Ok(profesorNuevosDatos);
            }
        }
        return ServiceResponse.NotFound(null);
    }

    public ServiceResponse<Profesor> deleteProfesor(int id) {
        for (Profesor profesor : profesores) {
            if (profesor.getId() == id) {
                profesores.remove(profesor);
                return ServiceResponse.Ok(null);
            }
        }
        return ServiceResponse.NotFound(null);
    }
}