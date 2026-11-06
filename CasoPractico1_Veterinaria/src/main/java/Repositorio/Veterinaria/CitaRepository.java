package Repositorio.Veterinaria;

import Domain.Veterinaria.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CitaRepository extends
        JpaRepository<Cita, Integer> {

    List<Cita> findAllByOrderByFechaAsc();
}
