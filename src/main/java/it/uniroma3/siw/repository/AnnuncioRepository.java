package it.uniroma3.siw.repository;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Annuncio;

public interface AnnuncioRepository extends CrudRepository<Annuncio, Long> {
	
	List<Annuncio> findAll();

    List<Annuncio> findByStato(Annuncio.Stato stato);

    List<Annuncio> findByCategoriaId(Long categoriaId);

    List<Annuncio> findByAutoreId(Long autoreId);

    List<Annuncio> findByTitoloContainingIgnoreCaseAndStato(String titolo, Annuncio.Stato stato);

    @EntityGraph(attributePaths = {"autore", "categoria"})
    List<Annuncio> findAllByStatoOrderByDataPubblicazioneDesc(Annuncio.Stato stato);
    
    @EntityGraph(attributePaths = {"categoria"})
    @Query("""
    		SELECT a
    		FROM Annuncio a
    		WHERE a.stato = it.uniroma3.siw.model.Annuncio.Stato.ATTIVO
    		AND (:nome IS NULL OR LOWER(a.titolo) LIKE LOWER(CONCAT('%', :nome, '%')))
    		AND (:categoria IS NULL OR a.categoria.id = :categoria)
    		AND (:prezzo IS NULL OR a.prezzo <= :prezzo)
    		ORDER BY a.dataPubblicazione DESC
    		""")
    		List<Annuncio> find(@Param("nome") String nome, @Param("categoria") Long categoria, @Param("prezzo") Double prezzo);
}
