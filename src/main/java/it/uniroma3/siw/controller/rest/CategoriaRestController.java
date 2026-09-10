package it.uniroma3.siw.controller.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.dto.CategoriaDTO;
import it.uniroma3.siw.service.CategoriaService;

@RestController
public class CategoriaRestController {

    private CategoriaService categoriaService;
    
    public CategoriaRestController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

    @GetMapping("/rest/categorie")
    public List<CategoriaDTO> getCategorie() {

        return categoriaService.findAll()
                .stream()
                .map(CategoriaDTO::new)
                .toList();
    }

}