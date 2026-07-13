package it.uniroma3.siw.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.exception.AnnuncioNotFoundException;
import it.uniroma3.siw.exception.UnauthorizedOperationException;
import it.uniroma3.siw.model.Annuncio;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Immagine;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.AnnuncioRepository;

@Service
public class AnnuncioService {

    private AnnuncioRepository annuncioRepository;
    
    public AnnuncioService(AnnuncioRepository annuncioRepository) {
		this.annuncioRepository = annuncioRepository;
	}
    
    @Transactional(readOnly = true)
    public List<Annuncio> findAll() {
    	return annuncioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Annuncio> findAllAttivi() {
        return annuncioRepository.findAllByStatoOrderByDataPubblicazioneDesc(Annuncio.Stato.ATTIVO);
    }

    @Transactional(readOnly = true)
    public Annuncio findById(Long id) {
        return annuncioRepository.findById(id)
                .orElseThrow(() -> new AnnuncioNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public Annuncio findByIdForUpdate(Long id, User utenteRichiedente) {
        Annuncio annuncio = this.findById(id);
        this.verificaProprietario(annuncio, utenteRichiedente);
        return annuncio;
    }

    @Transactional(readOnly = true)
    public List<Annuncio> findByCategoria(Long categoriaId) {
        return annuncioRepository.findByCategoriaId(categoriaId);
    }

    @Transactional(readOnly = true)
    public List<Annuncio> findByAutore(Long autoreId) {
        return annuncioRepository.findByAutoreId(autoreId);
    }

    @Transactional(readOnly = true)
    public List<Annuncio> findByTitolo(String titolo) {
        return annuncioRepository.findByTitoloContainingIgnoreCaseAndStato(titolo, Annuncio.Stato.ATTIVO);
    }
    
	@Transactional(readOnly = true)
	public List<Annuncio> search(String nome, Long categoria, Double prezzo){
	    return annuncioRepository.find(nome,categoria,prezzo);
	}
	
    @Transactional
    public Annuncio save(Annuncio annuncio) {
        if (annuncio.getDataPubblicazione() == null) {
            annuncio.setDataPubblicazione(LocalDateTime.now());
        }
        if (annuncio.getStato() == null) {
            annuncio.setStato(Annuncio.Stato.ATTIVO);
        }
        return annuncioRepository.save(annuncio);
    }

    @Transactional
    public Annuncio update(Long id,
                           Annuncio datiAggiornati,
                           MultipartFile immagineFile,
                           User utenteRichiedente) throws IOException {

        Annuncio annuncio = this.findById(id);
        this.verificaProprietario(annuncio, utenteRichiedente);

        annuncio.setTitolo(datiAggiornati.getTitolo());
        annuncio.setDescrizione(datiAggiornati.getDescrizione());
        annuncio.setPrezzo(datiAggiornati.getPrezzo());
        annuncio.setCategoria(datiAggiornati.getCategoria());

        if (immagineFile != null && !immagineFile.isEmpty()) {
        	Immagine immagine = new Immagine();
			immagine.setDati(immagineFile.getBytes());
			immagine.setContentType(immagineFile.getContentType());
			annuncio.setImmagine(immagine);
        }

        return annuncioRepository.save(annuncio);
    }

    @Transactional
    public void marcaComeVenduto(Long id, User utenteRichiedente) {
        Annuncio annuncio = this.findById(id);
        this.verificaProprietario(annuncio, utenteRichiedente);
        annuncio.setStato(Annuncio.Stato.VENDUTO);
        annuncioRepository.save(annuncio);
    }

    @Transactional
    public void delete(Long id, User utenteRichiedente) {
        Annuncio annuncio = this.findById(id);
        this.verificaProprietario(annuncio, utenteRichiedente);
        annuncioRepository.delete(annuncio);
    }

    public boolean canManage(Annuncio annuncio, User utenteRichiedente) {
        if (annuncio == null || utenteRichiedente == null) {
            return false;
        }

        boolean isAdmin = utenteRichiedente.getCredentials() != null
                && utenteRichiedente.getCredentials().getRole().equals(Credentials.ADMIN_ROLE);

        boolean isProprietario = annuncio.getAutore() != null
                && annuncio.getAutore().getId() != null
                && annuncio.getAutore().getId().equals(utenteRichiedente.getId());

        return isProprietario || isAdmin;
    }

    private void verificaProprietario(Annuncio annuncio, User utenteRichiedente) {
        if (!this.canManage(annuncio, utenteRichiedente)) {
            throw new UnauthorizedOperationException(
                    "Non sei autorizzato a modificare o cancellare questo annuncio.");
        }
    }
}
