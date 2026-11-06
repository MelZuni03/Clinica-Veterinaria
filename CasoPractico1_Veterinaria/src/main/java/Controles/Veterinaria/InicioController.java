package Controles.Veterinaria;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InicioController {

    @GetMapping("/")
    public String inicio() {
        return "inicio";
    }

    @PostMapping("/consultas")
    public String enviarConsulta(@RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam String mascota,
            @RequestParam String mensaje,
            RedirectAttributes redirectAttributes) {
        if (nombre.isBlank() || correo.isBlank() || mascota.isBlank() || mensaje.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Complete todos los campos del formulario.");
        } else {
            redirectAttributes.addFlashAttribute("mensajeExito",
                    "¡Gracias, " + nombre + "! Recibimos tu consulta y te contactaremos pronto.");
        }
        return "redirect:/#contacto";
    }
}
