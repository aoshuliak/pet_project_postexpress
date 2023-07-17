package com.postexpress.Postrexpress.controller;

import com.postexpress.Postrexpress.dto.UserDTO;
import com.postexpress.Postrexpress.model.Role;
import com.postexpress.Postrexpress.model.User;
import com.postexpress.Postrexpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserDTO());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("user") UserDTO user,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "login";
        }

        // Assuming you have a userService.authenticateUser() method to validate the user credentials
        boolean isAuthenticated = userService.authenticateUser(user.getEmail(), user.getPassword());

        if (isAuthenticated) {
            return "redirect:/users/all";
        } else {
            result.rejectValue("email", "error.user", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("user") UserDTO user,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setRole(Role.USER);
        User newUser = userService.create(UserDTO.transformToEntity(user));
        return "redirect:/users/all";
    }
}
