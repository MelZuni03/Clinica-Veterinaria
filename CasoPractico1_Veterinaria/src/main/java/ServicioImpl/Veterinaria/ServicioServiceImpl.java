package ServicioImpl.Veterinaria;

import Domain.Veterinaria.Servicio;
import Repositorio.Veterinaria.ServicioRepository;
import Servicio.Veterinaria.ServicioService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository repository;

    public ServicioServiceImpl(ServicioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Servicio> listarTodos() {
        return repository.findAllByOrderByNombreAsc();
    }

    @Override
    public Optional<Servicio> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Servicio guardar(Servicio servicio) {
        return repository.save(servicio);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}
