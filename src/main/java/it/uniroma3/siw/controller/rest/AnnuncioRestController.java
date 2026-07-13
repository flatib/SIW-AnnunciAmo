package it.uniroma3.siw.controller.rest;

import java.util.List;

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
    public List<AnnuncioDTO> search(
            @RequestParam(defaultValue = "") String nome,
            @RequestParam(required = false) Long categoria,
            @RequestParam(required = false) Double prezzo) {

        List<Annuncio> annunci = annuncioService.search(nome, categoria, prezzo);

        return annunci.stream()
                .map(AnnuncioDTO::new)
                .toList();
    }
}