package it.uniroma3.siw.controller.rest;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.dto.AnnuncioDTO;
import it.uniroma3.siw.model.Annuncio;
import it.uniroma3.siw.service.AnnuncioService;

@RestController
@RequestMapping("/rest/annunci")
public class AnnuncioRestController {

    
    private AnnuncioService annuncioService;
    
    public AnnuncioRestController(AnnuncioService annuncioService) {
		this.annuncioService = annuncioService;
	}

    @GetMapping("/search")
    public Page<AnnuncioDTO> search(
            @RequestParam(defaultValue = "") String nome,
            @RequestParam(required = false) Long categoria,
            @RequestParam(required = false) Double prezzo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Page<Annuncio> annunciPage = annuncioService.searchPaginated(nome, categoria, prezzo, page, size);
        return annunciPage.map(AnnuncioDTO::new);
    }
}