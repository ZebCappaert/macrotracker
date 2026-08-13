package diet.macrotracker.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import diet.macrotracker.jpa.User;
import diet.macrotracker.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String rawPassword, int calories, int protein, int carbs, int fat) {
        String hashedPassword = passwordEncoder.encode(rawPassword);

        User newUser = new User(username, hashedPassword, calories, protein, carbs, fat);

        return userRepository.save(newUser);
    }
}