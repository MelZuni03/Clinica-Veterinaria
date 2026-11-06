package ServicioImpl.Veterinaria;

import Domain.Veterinaria.TipoServicio;
import Repositorio.Veterinaria.TipoServicioRepository;
import Servicio.Veterinaria.TipoServicioService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TipoServicioServiceImpl implements TipoServicioService {

    private final TipoServicioRepository repository;

    public TipoServicioServiceImpl(TipoServicioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TipoServicio> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<TipoServicio> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public TipoServicio guardar(TipoServicio tipoServicio) {
        return repository.save(tipoServicio);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
