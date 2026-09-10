package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Annuncio;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.AnnuncioService;
import it.uniroma3.siw.service.CategoriaService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UserService userService;
	private AnnuncioService annuncioService;
	private CredentialsService credentialsService;
	private CategoriaService categoriaService;
	
	public AdminController(UserService userService, AnnuncioService annuncioService,
			CredentialsService credentialsService, CategoriaService categoriaService) {
		this.userService = userService;
		this.annuncioService = annuncioService;
		this.credentialsService = credentialsService;
		this.categoriaService = categoriaService;
	}
	
	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		return "admin/dashboard";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		return "admin/users";
	}
	
	@PostMapping("/users/{id}/delete")
	public String deleteUser(@PathVariable("id") Long id) {
		List<User> users = userService.findAll();
		int adminCount = (int) users.stream().filter(user -> user.getCredentials().getRole().equals(Credentials.ADMIN_ROLE)).count();
		if(adminCount == 1) {
			return "redirect:/admin/users?error=lastAdmin";
		}
		userService.deleteUser(id);
		return "redirect:/admin/users";
	}
	
	@PostMapping("/users/{id}/promote")
	public String promoteUser(@PathVariable("id") Long id) {
		User user = userService.findById(id);
		user.getCredentials().setRole(Credentials.ADMIN_ROLE);
		userService.saveUser(user);
		return "redirect:/admin/users";
	}
	
	@GetMapping("/annunci")
	public String listAnnunci(Model model) {
		model.addAttribute("annunci", annuncioService.findAll());
		return "admin/annunci";
	}
	
	@PostMapping("/annunci/{id}/delete")
	public String deleteAnnuncio(@PathVariable("id") Long id) {
		annuncioService.delete(id, getUtenteLoggato());
		return "redirect:/admin/annunci";
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
	
	@PostMapping("/annunci/{id}/edit")
	public String updateAnnuncio(@PathVariable("id") Long id, @Valid @ModelAttribute("annuncio") Annuncio annuncio,
	        BindingResult bindingResult,
	        @RequestParam(value = "immagineFile", required = false) MultipartFile immagineFile, Model model) throws IOException {
	    if (immagineFile != null && !immagineFile.isEmpty() && immagineFile.getSize() > 5 * 1024 * 1024) {
	        model.addAttribute("erroreImmagine", "L'immagine selezionata supera la dimensione massima di 5 MB.");
	        model.addAttribute("categorie", categoriaService.findAll());
	        model.addAttribute("isEdit", true);
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
	
	private User getUtenteLoggato() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return credentialsService.getCredentials(username).getUser();
	}
}
