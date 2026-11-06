package Servicio.Veterinaria;

import Domain.Veterinaria.TipoServicio;
import java.util.List;
import java.util.Optional;

public interface TipoServicioService {

    List<TipoServicio> listarTodos();

    Optional<TipoServicio> buscarPorId(Integer id);

    TipoServicio guardar(TipoServicio tipoServicio);

    void eliminar(Integer id);
}
