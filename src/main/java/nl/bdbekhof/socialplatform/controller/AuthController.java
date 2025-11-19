package nl.bdbekhof.socialplatform.controller;

import nl.bdbekhof.socialplatform.service.UserService;
import nl.bdbekhof.socialplatform.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) { this.userService = userService; }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String email,
                           @RequestParam String username,
                           @RequestParam String password) {
        if (userService.usernameExists(username)) {
            return "register";
        }
        userService.register(email, username, password);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() { return "login"; }
}
