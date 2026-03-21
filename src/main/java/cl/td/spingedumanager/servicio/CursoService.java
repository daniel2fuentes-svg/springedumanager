package cl.td.spingedumanager.servicio;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import cl.td.spingedumanager.modelo.Curso;
import cl.td.spingedumanager.repositorio.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;

    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    public void guardar(Curso curso) {
        repository.save(curso);
    }

    // Listar según el filtro
    public List<Curso> listarCursos(boolean mostrarTodos) {
        if (mostrarTodos) {
            return repository.findAll();
        }
        return repository.findByActivoTrue();
    }

    // EL BORRADO LÓGICO: No borra, solo desactiva
    public void desactivar(Long id) {
        Curso cur = repository.findById(id).orElseThrow();
        cur.setActivo(false); // Cambiamos el switch a apagado
        repository.save(cur); // Guardamos el cambio
    }

    // Método para volver a activar
    public void activar(Long id) {
        Curso cur = repository.findById(id).orElseThrow();
        cur.setActivo(true);
        repository.save(cur);
    }
}
