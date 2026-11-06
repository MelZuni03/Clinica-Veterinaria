package Controles.Veterinaria;

import Domain.Veterinaria.Servicio;
import Domain.Veterinaria.TipoServicio;
import Servicio.Veterinaria.ServicioService;
import Servicio.Veterinaria.TipoServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService servicioService;
    private final TipoServicioService tipoService;

    public ServicioController(ServicioService servicioService, TipoServicioService tipoService) {
        this.servicioService = servicioService;
        this.tipoService = tipoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("servicios", servicioService.listarTodos());
        return "servicios/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("tipos", tipoService.listarTodos());
        model.addAttribute("titulo", "Agregar servicio");
        return "servicios/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        return servicioService.buscarPorId(id).map(servicio -> {
            model.addAttribute("servicio", servicio);
            model.addAttribute("tipos", tipoService.listarTodos());
            model.addAttribute("titulo", "Editar servicio");
            return "servicios/formulario";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "El servicio no existe.");
            return "redirect:/servicios";
        });
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Servicio servicio, BindingResult result,
            @RequestParam(value = "tipoId", required = false) Integer tipoId,
            Model model, RedirectAttributes ra) {
        if (tipoId == null) {
            result.rejectValue("tipoServicio", "tipo.required", "Seleccione un tipo de servicio");
        } else {
            tipoService.buscarPorId(tipoId).ifPresentOrElse(servicio::setTipoServicio,
                    () -> result.rejectValue("tipoServicio", "tipo.invalid", "El tipo seleccionado no existe"));
        }
        if (result.hasErrors()) {
            model.addAttribute("tipos", tipoService.listarTodos());
            model.addAttribute("titulo", servicio.getId() == null ? "Agregar servicio" : "Editar servicio");
            return "servicios/formulario";
        }
        servicioService.guardar(servicio);
        ra.addFlashAttribute("mensajeExito", "Servicio guardado correctamente.");
        return "redirect:/servicios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            servicioService.eliminar(id);
            ra.addFlashAttribute("mensajeExito", "Servicio eliminado correctamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "No se puede eliminar porque tiene citas asociadas.");
        }
        return "redirect:/servicios";
    }
}
