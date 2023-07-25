package com.postexpress.Postrexpress.controller;

import com.postexpress.Postrexpress.dto.UserDTO;
import com.postexpress.Postrexpress.model.Package;
import com.postexpress.Postrexpress.model.Role;
import com.postexpress.Postrexpress.model.User;
import com.postexpress.Postrexpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/read")
    @PreAuthorize("authentication.principal.id == #id or hasAuthority('ADMIN')")
    public String read(@PathVariable long id,
                       Model model,
                       Authentication authentication) {
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"));
        User user = userService.readById(id);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("user", user);
        return "user-info";
    }

    @GetMapping("/{id}/update")
    @PreAuthorize("authentication.principal.id == #id or hasAuthority('ADMIN')")
    public String update(@PathVariable long id,
                         Model model,
                         Authentication authentication) {
        User user = userService.readById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "update-user";
    }

    @PostMapping("/{id}/update")
    @PreAuthorize("authentication.principal.id == #id or hasAuthority('ADMIN')")
    public String update(@PathVariable long id,
                         Model model,
                         @Validated @ModelAttribute("user") UserDTO userDTO,
                         BindingResult result,
                         @RequestParam("role") Role role,
                         Authentication authentication) {
        if (result.hasErrors()) {
            userDTO.setRole(userService.readById(id).getRole());
            model.addAttribute("user", userDTO);
            model.addAttribute("roles", Role.values());
            return "update-user";
        }

        User user = userService.readById(id);

        User oldUser = userService.readById(id);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        if (oldUser.getRole().equals(Role.USER)) {
            user.setRole(oldUser.getRole());
        } else {
            user.setRole(role);
        }
        userService.update(UserDTO.transformToEntity(userDTO));
        return "redirect:/users/" + id + "/read";
    }

    @GetMapping("/{id}/delete")
    @PreAuthorize("authentication.principal.id == #id or hasAuthority('ADMIN')")
    public String delete(@PathVariable("id") long id,
                         Authentication authentication){
        User user = userService.readById(id);
        userService.delete(id);
        return "redirect:/users/all";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAll(Model model,
                         Authentication authentication) {
        model.addAttribute("users", userService.getAll());
        return "users-list";
    }
}