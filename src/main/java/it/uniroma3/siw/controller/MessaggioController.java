package it.uniroma3.siw.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siw.exception.UnauthorizedOperationException;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.MessaggioService;

@Controller
public class MessaggioController {
	
    private MessaggioService messaggioService;
    private CredentialsService credentialsService;
    
    public MessaggioController(MessaggioService messaggioService, CredentialsService credentialsService) {
		this.messaggioService = messaggioService;
		this.credentialsService = credentialsService;
	}

    @PostMapping("/annunci/{id}/messaggi")
    public String inviaMessaggio(@PathVariable("id") Long annuncioId,
                                 @RequestParam("testo") String testo,
                                 RedirectAttributes redirectAttributes) {
        User mittente = getUtenteLoggato();

        try {
            messaggioService.invia(annuncioId, testo, mittente);
            redirectAttributes.addFlashAttribute("messaggioSuccesso", "Messaggio inviato al venditore.");
        } catch (IllegalArgumentException | UnauthorizedOperationException e) {
            redirectAttributes.addFlashAttribute("messaggioErrore", e.getMessage());
        }

        return "redirect:/annunci/" + annuncioId;
    }

    private User getUtenteLoggato() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getName())) {
            throw new UnauthorizedOperationException("Devi effettuare il login per inviare un messaggio.");
        }

        String username = authentication.getName();
        return credentialsService.getCredentials(username).getUser();
    }
}
