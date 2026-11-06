package Servicio.Veterinaria;

import Domain.Veterinaria.Servicio;
import java.util.List;
import java.util.Optional;

public interface ServicioService {

    List<Servicio> listarTodos();

    Optional<Servicio> buscarPorId(Integer id);

    Servicio guardar(Servicio servicio);

    void eliminar(Integer id);
}
