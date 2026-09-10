package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import it.uniroma3.siw.exception.DuplicateUsernameException;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;

@Controller
public class RegisterController {

    private UserService userService;
    private CredentialsService credentialsService;
    
    public RegisterController(UserService userService, CredentialsService credentialsService) {
		this.userService = userService;
		this.credentialsService = credentialsService;
	}

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                            BindingResult userBindingResult,
                            @Valid @ModelAttribute("credentials") Credentials credentials,
                            BindingResult credentialsBindingResult,
                            Model model) {

        if (userBindingResult.hasErrors() || credentialsBindingResult.hasErrors()) {
            return "register";
        }

        try {
            User savedUser = userService.saveUser(user);
            credentials.setUser(savedUser);
            credentialsService.saveCredentials(credentials);
        } catch (DuplicateUsernameException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }

        return "redirect:/login";
    }
}
