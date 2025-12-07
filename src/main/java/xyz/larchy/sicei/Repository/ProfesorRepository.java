package xyz.larchy.sicei.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.larchy.sicei.Models.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
}
