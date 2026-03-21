package cl.td.spingedumanager.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portal") // Ruta protegida para estudiantes
public class PortalController {

    @GetMapping("/dashboard")
    public String dashboardPortal(Model model) {
        // Aquí simularemos datos para la vista
        model.addAttribute("nombreEstudiante", "Daniel Fuentes");
        model.addAttribute("cursoActual", "Desarrollo Java FullStack");
        model.addAttribute("progreso", 75); // Porcentaje para la barra de avance

        return "portal/dashboard"; // Ruta del archivo HTML
    }
}