package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import it.uniroma3.siw.model.Annuncio;
import it.uniroma3.siw.model.Annuncio.Stato;
import it.uniroma3.siw.model.Immagine;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.AnnuncioService;
import it.uniroma3.siw.service.CategoriaService;
import it.uniroma3.siw.service.CredentialsService;

@Controller
public class AnnuncioController {

	private AnnuncioService annuncioService;
	private CategoriaService categoriaService;
	private CredentialsService credentialsService;
	
	public AnnuncioController(AnnuncioService annuncioService, CategoriaService categoriaService,
			CredentialsService credentialsService) {
		this.annuncioService = annuncioService;
		this.categoriaService = categoriaService;
		this.credentialsService = credentialsService;
	}

	@GetMapping({ "/", "/index", "/annunci" })
	public String listAnnunci(Model model) {
		List<Annuncio> annunci = annuncioService.findAllAttivi();
		model.addAttribute("annunci", annunci);
		model.addAttribute("utenteLoggato", getUtenteLoggatoSePresente());
		return "annunci/list";
	}

	@GetMapping("/annunci/{id}")
	public String getAnnuncio(@PathVariable("id") Long id, Model model) {
		Annuncio annuncio = annuncioService.findById(id);
		User utenteLoggato = getUtenteLoggatoSePresente();

		boolean puoGestireAnnuncio = annuncioService.canManage(annuncio, utenteLoggato);
		boolean puoContattareVenditore = utenteLoggato != null
				&& !puoGestireAnnuncio
				&& annuncio.getStato() == Stato.ATTIVO;

		model.addAttribute("annuncio", annuncio);
		model.addAttribute("utenteLoggato", utenteLoggato);
		model.addAttribute("puoGestireAnnuncio", puoGestireAnnuncio);
		model.addAttribute("puoContattareVenditore", puoContattareVenditore);
		return "annunci/detail";
	}

	@GetMapping("/annunci/new")
	public String newAnnuncio(Model model) {
		model.addAttribute("annuncio", new Annuncio());
		model.addAttribute("categorie", categoriaService.findAll());
		model.addAttribute("isEdit", false);
		return "annunci/form";
	}

	@PostMapping("/annunci")
	public String saveAnnuncio(@Valid @ModelAttribute("annuncio") Annuncio annuncio, BindingResult bindingResult,
			@RequestParam("immagineFile") MultipartFile immagineFile, Model model) throws IOException {
		if (bindingResult.hasErrors()) {
			model.addAttribute("categorie", categoriaService.findAll());
			model.addAttribute("isEdit", false);
			return "annunci/form";
		}
		if (!immagineFile.isEmpty()
	            && immagineFile.getSize() > 5 * 1024 * 1024) {

	        model.addAttribute(
	                "erroreImmagine",
	                "L'immagine selezionata supera la dimensione massima di 5 MB."
	        );
	        model.addAttribute("categorie", categoriaService.findAll());
	        model.addAttribute("isEdit", false);

	        return "annunci/form";
	    }

	    if (bindingResult.hasErrors()) {
	        model.addAttribute("categorie", categoriaService.findAll());
	        model.addAttribute("isEdit", false);
	        return "annunci/form";
	    }
	    Immagine immagine = new Immagine();
		if (!immagineFile.isEmpty()) {
			immagine.setDati(immagineFile.getBytes());
			immagine.setContentType(immagineFile.getContentType());
			annuncio.setImmagine(immagine);
		}
		else {
            try {
            	byte[] defaultImageData = new ClassPathResource("static/images/default.png").getInputStream().readAllBytes();
                immagine.setDati(defaultImageData);
                immagine.setContentType("image/png");
                annuncio.setImmagine(immagine);
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }
		User autore = getUtenteLoggato();
		annuncio.setAutore(autore);
		annuncio.setStato(Stato.ATTIVO);
		annuncioService.save(annuncio);
		return "redirect:/";
	}

	@GetMapping("/annunci/{id}/edit")
	public String editAnnuncio(@PathVariable("id") Long id, Model model) {
		User utente = getUtenteLoggato();
		Annuncio annuncio = annuncioService.findByIdForUpdate(id, utente);
		model.addAttribute("annuncio", annuncio);
		model.addAttribute("categorie", categoriaService.findAll());
		model.addAttribute("isEdit", true);
		return "annunci/form";
	}

	@GetMapping("/annunci/{id}/immagine")
	public ResponseEntity<byte[]> getImmagine(@PathVariable("id") Long id) {
		Annuncio annuncio = annuncioService.findById(id);
		Immagine img = annuncio.getImmagine();
		if (img == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(img.getContentType())).body(img.getDati());
	}

	@PostMapping("/annunci/{id}/edit")
	public String updateAnnuncio(@PathVariable("id") Long id, @Valid @ModelAttribute("annuncio") Annuncio annuncio,
			BindingResult bindingResult,
			@RequestParam(value = "immagineFile", required = false) MultipartFile immagineFile, Model model) throws IOException {

		if (!immagineFile.isEmpty()&& immagineFile.getSize() > 5 * 1024 * 1024) {

	        model.addAttribute("erroreImmagine", "L'immagine selezionata supera la dimensione massima di 5 MB.");
	        model.addAttribute("categorie", categoriaService.findAll());
	        model.addAttribute("isEdit", false);

	        return "annunci/form";
	    }

	    if (bindingResult.hasErrors()) {
	        model.addAttribute("categorie", categoriaService.findAll());
	        model.addAttribute("isEdit", false);
	        return "annunci/form";
	    }
		
		if (bindingResult.hasErrors()) {
			annuncio.setId(id);

			Annuncio annuncioEsistente = annuncioService.findById(id);
			annuncio.setImmagine(annuncioEsistente.getImmagine());

			model.addAttribute("categorie", categoriaService.findAll());
			model.addAttribute("isEdit", true);
			return "annunci/form";
		}

		User utente = getUtenteLoggato();
		annuncioService.update(id, annuncio, immagineFile, utente);

		return "redirect:/annunci/" + id;
	}

	@PostMapping("/annunci/{id}/venduto")
	public String marcaComeVenduto(@PathVariable("id") Long id) {
		User utente = getUtenteLoggato();
		annuncioService.marcaComeVenduto(id, utente);
		return "redirect:/annunci/" + id;
	}

	@PostMapping("/annunci/{id}/delete")
	public String deleteAnnuncio(@PathVariable("id") Long id,
			@RequestParam(value = "redirectTo", required = false) String redirectTo) {
		User utente = getUtenteLoggato();
		annuncioService.delete(id, utente);

		if ("/dashboard".equals(redirectTo)) {
			return "redirect:/dashboard";
		}

		return "redirect:/";
	}

	@GetMapping("/annunci/search")
	public String search(@RequestParam(required = false) String nome, @RequestParam(required = false) Long categoria,
			@RequestParam(required = false) Double prezzo, Model model) {

		model.addAttribute("annunci", annuncioService.search(nome, categoria, prezzo));
		model.addAttribute("categorie", categoriaService.findAll());
		return "annunci/list";
	}

	private User getUtenteLoggato() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return credentialsService.getCredentials(username).getUser();
	}

	private User getUtenteLoggatoSePresente() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()
				|| "anonymousUser".equals(authentication.getPrincipal())) {
			return null;
		}

		return credentialsService.getCredentials(authentication.getName()).getUser();
	}
}
