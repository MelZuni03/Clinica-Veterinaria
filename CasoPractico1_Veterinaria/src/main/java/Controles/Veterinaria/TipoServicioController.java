package Controles.Veterinaria;

import Domain.Veterinaria.TipoServicio;
import Servicio.Veterinaria.TipoServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tipos")
public class TipoServicioController {

    private final TipoServicioService service;

    public TipoServicioController(TipoServicioService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tipos", service.listarTodos());
        return "tipos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("tipoServicio", new TipoServicio());
        model.addAttribute("titulo", "Agregar tipo de servicio");
        return "tipos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        return service.buscarPorId(id).map(tipo -> {
            model.addAttribute("tipoServicio", tipo);
            model.addAttribute("titulo", "Editar tipo de servicio");
            return "tipos/formulario";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "El tipo de servicio no existe.");
            return "redirect:/tipos";
        });
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute TipoServicio tipoServicio, BindingResult result,
            Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", tipoServicio.getId() == null ? "Agregar tipo de servicio" : "Editar tipo de servicio");
            return "tipos/formulario";
        }
        service.guardar(tipoServicio);
        ra.addFlashAttribute("mensajeExito", "Tipo de servicio guardado correctamente.");
        return "redirect:/tipos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            service.eliminar(id);
            ra.addFlashAttribute("mensajeExito", "Tipo de servicio eliminado "
                    + "correctamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "No se puede eliminar: primero "
                    + "elimine los servicios asociados.");
        }
        return "redirect:/tipos";
    }
}
