package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.exception.UserNotFoundException;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
    
    @Transactional(readOnly = true)
    public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = this.findById(id);
        userRepository.delete(user);
    }
}
