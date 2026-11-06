package Repositorio.Veterinaria;

import Domain.Veterinaria.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoServicioRepository extends 
        JpaRepository<TipoServicio, Integer> {
}
