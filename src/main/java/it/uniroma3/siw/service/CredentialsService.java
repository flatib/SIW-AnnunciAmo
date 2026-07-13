package it.uniroma3.siw.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.exception.DuplicateUsernameException;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredentialsRepository;

@Service
public class CredentialsService {

    private CredentialsRepository credentialsRepository;
    private PasswordEncoder passwordEncoder;
    
    public CredentialsService(CredentialsRepository credentialsRepository, PasswordEncoder passwordEncoder) {
		this.credentialsRepository = credentialsRepository;
		this.passwordEncoder = passwordEncoder;
	}

    @Transactional(readOnly = true)
    public Credentials getCredentials(Long id) {
        return credentialsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credenziali con ID " + id + " non trovate."));
    }

    @Transactional(readOnly = true)
    public Credentials getCredentials(String username) {
        return credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Credenziali per username " + username + " non trovate."));
    }

    @Transactional
    public Credentials saveCredentials(Credentials credentials) {
        if (credentialsRepository.existsByUsername(credentials.getUsername())) {
            throw new DuplicateUsernameException(credentials.getUsername());
        }
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        credentials.setRole(Credentials.DEFAULT_ROLE);
        return credentialsRepository.save(credentials);
    }
    
    @Transactional
    public Credentials saveAdminCredentials(Credentials credentials) {
		if (credentialsRepository.existsByUsername(credentials.getUsername())) {
			throw new DuplicateUsernameException(credentials.getUsername());
		}
		credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
		credentials.setRole(Credentials.ADMIN_ROLE);
		return credentialsRepository.save(credentials);
	} 
}
