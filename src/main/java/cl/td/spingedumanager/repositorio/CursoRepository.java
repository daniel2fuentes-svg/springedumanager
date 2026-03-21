package cl.td.spingedumanager.repositorio;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import cl.td.spingedumanager.modelo.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Para listar solo los activos (la vista por defecto)
    List<Curso> findByActivoTrue();

    // Para listar absolutamente todos (cuando marques el switch)
    List<Curso> findAll();
}