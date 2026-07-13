package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Messaggio;
import it.uniroma3.siw.model.User;

public interface MessaggioRepository extends CrudRepository<Messaggio, Long> {

	List<Messaggio> findByAnnuncioIdOrderByDataInvioAsc(Long annuncioId);

	List<Messaggio> findByMittenteId(Long mittenteId);

	List<Messaggio> findByMittenteIdOrderByDataInvioDesc(Long mittenteId);

	List<Messaggio> findByAnnuncio_Autore_IdOrderByDataInvioDesc(Long autoreId);

	long countByAnnuncioId(Long annuncioId);

	@Query("""
			SELECT m
			FROM Messaggio m
			JOIN FETCH m.annuncio a
			JOIN FETCH m.mittente mittente
			JOIN FETCH m.destinatario destinatario
			WHERE m.mittente = :utente
			   OR m.destinatario = :utente
			ORDER BY m.dataInvio DESC
			""")
	List<Messaggio> findMessaggiUtente(@Param("utente") User utente);

	@Query("""
			SELECT m
			FROM Messaggio m
			JOIN FETCH m.mittente
			JOIN FETCH m.destinatario
			JOIN FETCH m.annuncio
			WHERE m.annuncio.id = :annuncioId
			  AND (
			       (m.mittente.id = :utenteId AND m.destinatario.id = :altroUtenteId)
			       OR
			       (m.mittente.id = :altroUtenteId AND m.destinatario.id = :utenteId)
			  )
			ORDER BY m.dataInvio ASC
			""")
	List<Messaggio> findConversazione(@Param("annuncioId") Long annuncioId, @Param("utenteId") Long utenteId,
			@Param("altroUtenteId") Long altroUtenteId);
}
