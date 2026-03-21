package cl.td.spingedumanager.restController;

import cl.td.spingedumanager.modelo.Estudiante;
import cl.td.spingedumanager.repositorio.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteRestController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Listar todos (GET)
    @GetMapping
    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    // Crear nuevo (POST)
    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // Buscar por ID (GET)
    @GetMapping("/{id}")
    public Estudiante obtenerPorId(@PathVariable Long id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    // Eliminar (DELETE)
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        estudianteRepository.deleteById(id);
    }
}