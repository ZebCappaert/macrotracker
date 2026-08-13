package diet.macrotracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import diet.macrotracker.jpa.User;
import diet.macrotracker.repository.UserRepository;
import diet.macrotracker.service.AuthService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    private final AuthService authService;

    public UserController(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile() {
        User user = authService.getLoggedInUser();
        return ResponseEntity.ok(user);
    }

    @PutMapping("/goals")
    public ResponseEntity<User> updateGoals(@RequestBody User updatedGoals) {
        User user = authService.getLoggedInUser();

        user.setTargetCalories(updatedGoals.getTargetCalories());
        user.setTargetProtein(updatedGoals.getTargetProtein());
        user.setTargetCarbs(updatedGoals.getTargetCarbs());
        user.setTargetFat(updatedGoals.getTargetFat());

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}