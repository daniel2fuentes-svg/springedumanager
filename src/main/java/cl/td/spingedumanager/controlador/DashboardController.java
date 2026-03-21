package cl.td.spingedumanager.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/") // Para que sea la página de inicio al entrar al proyecto
    public String index() {
        return "dashboard"; // Nombre del HTML que creamos
    }

    @GetMapping("/dashboard") // Opcional: acceso directo por URL /dashboard
    public String dashboard() {
        return "dashboard";
    }
}