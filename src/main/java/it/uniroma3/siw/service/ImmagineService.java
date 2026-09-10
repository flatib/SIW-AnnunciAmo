package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Immagine;
import it.uniroma3.siw.repository.ImmagineRepository;

@Service
public class ImmagineService {

    private ImmagineRepository immagineRepository;
    
    public ImmagineService(ImmagineRepository immagineRepository) {
		this.immagineRepository = immagineRepository;
	}

    @Transactional
    public Immagine save(Immagine immagine) {
        return this.immagineRepository.save(immagine);
    }

    @Transactional(readOnly = true)
    public Immagine getImmagine(Long id) {
        Optional<Immagine> result = this.immagineRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional(readOnly = true)
    public Iterable<Immagine> getAllImmagini() {
        return this.immagineRepository.findAll();
    }

    @Transactional
    public void deleteImmagine(Long id) {
        this.immagineRepository.deleteById(id);
    }
}