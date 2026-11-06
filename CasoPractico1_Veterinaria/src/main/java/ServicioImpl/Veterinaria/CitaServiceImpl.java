package ServicioImpl.Veterinaria;

import Domain.Veterinaria.Cita;
import Repositorio.Veterinaria.CitaRepository;
import Servicio.Veterinaria.CitaService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository repository;

    public CitaServiceImpl(CitaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cita> listarTodas() {
        return repository.findAllByOrderByFechaAsc();
    }
}
