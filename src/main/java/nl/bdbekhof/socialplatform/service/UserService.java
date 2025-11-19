package nl.bdbekhof.socialplatform.service;

import nl.bdbekhof.socialplatform.model.User;
import nl.bdbekhof.socialplatform.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String email, String username, String plainPassword) {
        User u = new User();
        u.setEmail(email);
        u.setUsername(username);
        u.setPasswordHash(encoder.encode(plainPassword));
        return userRepository.save(u);
    }

    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
