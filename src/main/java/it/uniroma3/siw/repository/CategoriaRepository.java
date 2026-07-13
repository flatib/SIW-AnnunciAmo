package it.uniroma3.siw.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nome);

    boolean existsByNome(String nome);
}
