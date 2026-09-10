package it.uniroma3.siw.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.dto.ChatPreviewDTO;
import it.uniroma3.siw.exception.AnnuncioNotFoundException;
import it.uniroma3.siw.exception.UnauthorizedOperationException;
import it.uniroma3.siw.exception.UserNotFoundException;
import it.uniroma3.siw.model.Annuncio;
import it.uniroma3.siw.model.Messaggio;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.AnnuncioRepository;
import it.uniroma3.siw.repository.MessaggioRepository;
import it.uniroma3.siw.repository.UserRepository;

@Service
public class MessaggioService {

	private MessaggioRepository messaggioRepository;
	private AnnuncioRepository annuncioRepository;
	private UserRepository userRepository;
	
	public MessaggioService(MessaggioRepository messaggioRepository, AnnuncioRepository annuncioRepository, UserRepository userRepository) {
		this.messaggioRepository = messaggioRepository;
		this.annuncioRepository = annuncioRepository;
		this.userRepository = userRepository;
	}

	@Transactional(readOnly = true)
	public List<Messaggio> findByAnnuncio(Long annuncioId) {
		return messaggioRepository.findByAnnuncioIdOrderByDataInvioAsc(annuncioId);
	}

	@Transactional(readOnly = true)
	public List<Messaggio> findRicevuti(User destinatario) {
		return messaggioRepository.findByAnnuncio_Autore_IdOrderByDataInvioDesc(destinatario.getId());
	}

	@Transactional(readOnly = true)
	public List<Messaggio> findInviati(User mittente) {
		return messaggioRepository.findByMittenteIdOrderByDataInvioDesc(mittente.getId());
	}

	@Transactional
	public Messaggio invia(Long annuncioId, String testo, User mittente) {
		if (mittente == null) {
			throw new UnauthorizedOperationException("Devi effettuare il login per inviare un messaggio.");
		}

		if (testo == null || testo.isBlank()) {
			throw new IllegalArgumentException("Il messaggio non può essere vuoto.");
		}

		Annuncio annuncio = annuncioRepository.findById(annuncioId)
				.orElseThrow(() -> new AnnuncioNotFoundException(annuncioId));

		if (annuncio.getAutore() != null && annuncio.getAutore().getId() != null
				&& annuncio.getAutore().getId().equals(mittente.getId())) {
			throw new UnauthorizedOperationException("Non puoi contattare te stesso per un tuo annuncio.");
		}

		if (annuncio.getStato() != Annuncio.Stato.ATTIVO) {
			throw new UnauthorizedOperationException("Puoi contattare il venditore solo per annunci attivi.");
		}

		Messaggio messaggio = new Messaggio();
		messaggio.setTesto(testo.trim());
		messaggio.setAnnuncio(annuncio);
		messaggio.setMittente(mittente);
		messaggio.setDataInvio(LocalDateTime.now());
		messaggio.setDestinatario(annuncio.getAutore());

		return messaggioRepository.save(messaggio);
	}

	private void verificaPartecipazioneChat(Annuncio annuncio, User utenteCorrente, User altroUtente) {
		boolean utenteCorrenteVenditore = annuncio.getAutore().getId().equals(utenteCorrente.getId());
		boolean altroUtenteVenditore = annuncio.getAutore().getId().equals(altroUtente.getId());

		if (!utenteCorrenteVenditore && !altroUtenteVenditore) {
			throw new IllegalArgumentException("La chat deve coinvolgere il venditore dell'annuncio");
		}
	}

	@Transactional
	public Messaggio rispondi(Long annuncioId, Long altroUtenteId, String testo, User mittente) {

		Annuncio annuncio = annuncioRepository.findById(annuncioId)
				.orElseThrow(() -> new AnnuncioNotFoundException(annuncioId));
		User altroUtente = userRepository.findById(altroUtenteId)
				.orElseThrow(() -> new UserNotFoundException(altroUtenteId));

		if (testo == null || testo.isBlank()) {
			throw new IllegalArgumentException("Il messaggio non può essere vuoto");
		}

		if (mittente.getId().equals(altroUtente.getId())) {
			throw new IllegalArgumentException("Non puoi inviare un messaggio a te stesso");
		}

		verificaPartecipazioneChat(annuncio, mittente, altroUtente);

		Messaggio messaggio = new Messaggio();
		messaggio.setAnnuncio(annuncio);
		messaggio.setMittente(mittente);
		messaggio.setDestinatario(altroUtente);
		messaggio.setTesto(testo);
		messaggio.setDataInvio(LocalDateTime.now());

		return messaggioRepository.save(messaggio);
	}

	@Transactional(readOnly = true)
	public List<ChatPreviewDTO> findChatAperte(User utente) {
		List<Messaggio> messaggi = messaggioRepository.findMessaggiUtente(utente);

		Map<String, ChatPreviewDTO> chatMap = new LinkedHashMap<>();

		for (Messaggio messaggio : messaggi) {
			User altroUtente = messaggio.getMittente().getId().equals(utente.getId()) ? messaggio.getDestinatario()
					: messaggio.getMittente();

			String chiave = messaggio.getAnnuncio().getId() + "-" + altroUtente.getId();

			if (!chatMap.containsKey(chiave)) {
				ChatPreviewDTO preview = new ChatPreviewDTO(messaggio.getAnnuncio().getId(),
						messaggio.getAnnuncio().getTitolo(), altroUtente.getId(),
						altroUtente.getCredentials().getUsername(), messaggio.getTesto(),
						messaggio.getDataInvio());

				chatMap.put(chiave, preview);
			}
		}

		return new ArrayList<>(chatMap.values());
	}
	
	@Transactional(readOnly = true)
	public List<Messaggio> findConversazione(Long annuncioId, Long altroUtenteId, User utenteCorrente) {
	    Annuncio annuncio = annuncioRepository.findById(annuncioId).orElseThrow(() -> new AnnuncioNotFoundException(annuncioId));;
	    User altroUtente = userRepository.findById(altroUtenteId).orElseThrow(() -> new UserNotFoundException(altroUtenteId));;

	    verificaPartecipazioneChat(annuncio, utenteCorrente, altroUtente);

	    return messaggioRepository.findConversazione(
	            annuncioId,
	            utenteCorrente.getId(),
	            altroUtenteId
	    );
	}

	@Transactional
	public Messaggio invia(String testo, Annuncio annuncio, User mittente) {
		Messaggio messaggio = new Messaggio(testo, annuncio, mittente);
		messaggio.setDataInvio(LocalDateTime.now());
		return messaggioRepository.save(messaggio);
	}

	@Transactional
	public void delete(Long id) {
		messaggioRepository.deleteById(id);
	}
}
