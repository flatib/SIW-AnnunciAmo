package it.uniroma3.siw.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.AnnuncioService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.MessaggioService;

@Controller
public class DashboardController {

    private CredentialsService credentialsService;
    private AnnuncioService annuncioService;
    private MessaggioService messaggioService;
    
    public DashboardController(CredentialsService credentialsService,
							   AnnuncioService annuncioService,
							   MessaggioService messaggioService) {
		this.credentialsService = credentialsService;
		this.annuncioService = annuncioService;
		this.messaggioService = messaggioService;
	}

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {

        Credentials credentials =
                credentialsService.getCredentials(principal.getName());

        User user = credentials.getUser();

        model.addAttribute("utente", user);
        model.addAttribute("annunci",
                annuncioService.findByAutore(user.getId()));
        model.addAttribute("messaggiRicevuti",
                messaggioService.findRicevuti(user));
        model.addAttribute("messaggiInviati",
                messaggioService.findInviati(user));

        return "dashboard.html";
    }

}