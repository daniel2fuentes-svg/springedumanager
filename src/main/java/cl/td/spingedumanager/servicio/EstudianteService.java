package cl.td.spingedumanager.servicio;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import cl.td.spingedumanager.modelo.Estudiante;
import cl.td.spingedumanager.repositorio.EstudianteRepository;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository repository;

    public List<Estudiante> listarTodos() {
        return repository.findAll();
    }

    public void guardar(Estudiante estudiante) {
        repository.save(estudiante);
    }

    // Listar según el filtro
    public List<Estudiante> listarEstudiantes(boolean mostrarTodos) {
        if (mostrarTodos) {
            return repository.findAll();
        }
        return repository.findByActivoTrue();
    }

    // EL BORRADO LÓGICO: No borra, solo desactiva
    public void desactivar(Long id) {
        Estudiante est = repository.findById(id).orElseThrow();
        est.setActivo(false); // Cambiamos el switch a apagado
        repository.save(est); // Guardamos el cambio
    }

    // Método para volver a activar
    public void activar(Long id) {
        Estudiante est = repository.findById(id).orElseThrow();
        est.setActivo(true);
        repository.save(est);
    }
}
