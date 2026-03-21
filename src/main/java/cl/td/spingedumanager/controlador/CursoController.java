package cl.td.spingedumanager.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import cl.td.spingedumanager.servicio.CursoService;
import cl.td.spingedumanager.modelo.Curso;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping
    public String listar(@RequestParam(name = "todos", defaultValue = "false") boolean todos, Model model) {
        model.addAttribute("lista", service.listarCursos(todos));
        model.addAttribute("mostrandoTodos", todos); // Enviamos el estado a la vista
        return "cursos/lista";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Curso curso) {
        service.guardar(curso);
        return "redirect:/cursos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        // Buscamos al estudiante por su ID
        Curso curso = service.listarCursos(true) // Buscamos en todos
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);

        model.addAttribute("curso", curso);
        return "cursos/editar"; // Nos manda a la nueva pantalla
    }

    // Ruta para el borrado lógico
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.desactivar(id);
        return "redirect:/cursos";
    }

    // Ruta para activar
    @PostMapping("/activar/{id}")
    public String activar(@PathVariable Long id) {
        service.activar(id);
        return "redirect:/cursos";
    }
}