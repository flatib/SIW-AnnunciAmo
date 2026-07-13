package it.uniroma3.siw.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.MessaggioService;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private MessaggioService messaggioService;
    private CredentialsService credentialsService;

    public ChatController(MessaggioService messaggioService, CredentialsService credentialsService) {
        this.messaggioService = messaggioService;
        this.credentialsService = credentialsService;
    }

    @GetMapping
    public String listaChat(Model model) {
        User utente = getUtenteLoggato();

        model.addAttribute("chatAperte", messaggioService.findChatAperte(utente));

        return "chat/list";
    }

    @GetMapping("/annuncio/{annuncioId}/utente/{altroUtenteId}")
    public String dettaglioChat(@PathVariable Long annuncioId,
                                @PathVariable Long altroUtenteId,
                                Model model) {
        User utente = getUtenteLoggato();

        model.addAttribute("messaggi", messaggioService.findConversazione(annuncioId, altroUtenteId, utente));
        model.addAttribute("annuncioId", annuncioId);
        model.addAttribute("altroUtenteId", altroUtenteId);
        model.addAttribute("utenteCorrente", utente);

        return "chat/detail";
    }

    @PostMapping("/annuncio/{annuncioId}/utente/{altroUtenteId}")
    public String rispondi(@PathVariable Long annuncioId,
                           @PathVariable Long altroUtenteId,
                           @RequestParam("testo") String testo) {
        User utente = getUtenteLoggato();

        messaggioService.rispondi(annuncioId, altroUtenteId, testo, utente);

        return "redirect:/chat/annuncio/" + annuncioId + "/utente/" + altroUtenteId;
    }

    private User getUtenteLoggato() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return credentialsService.getCredentials(username).getUser();
    }
}