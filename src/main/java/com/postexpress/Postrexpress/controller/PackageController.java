package com.postexpress.Postrexpress.controller;

import com.postexpress.Postrexpress.dto.PackageDTO;
import com.postexpress.Postrexpress.dto.PackageTransformer;
import com.postexpress.Postrexpress.model.Package;
import com.postexpress.Postrexpress.model.Role;
import com.postexpress.Postrexpress.model.Status;
import com.postexpress.Postrexpress.model.User;
import com.postexpress.Postrexpress.service.PackageService;
import com.postexpress.Postrexpress.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/packages")
public class PackageController {

    private final PackageService packageService;
    private final UserService userService;

    public PackageController(PackageService packageService, UserService userService) {
        this.packageService = packageService;
        this.userService = userService;
    }

    @GetMapping("/{user_id}/create")
    public String create(@PathVariable("user_id") long userId,
                         Model model,
                         Authentication authentication){
        User user = userService.findByEmail(authentication.getName());
        if (userId != user.getId() && !user.getRole().equals(Role.ADMIN) ) {
            throw new AccessDeniedException("Access Denied");
        }

        List<User> recipients = userService.getAll();

        model.addAttribute("package", new Package());
        model.addAttribute("status", Status.values());
        model.addAttribute("user", userId);
        model.addAttribute("users", recipients);
        return "create_package";

    }

    @PostMapping("/{user_id}/create")
    public String create(@PathVariable("user_id") long userId,
                         @Validated @ModelAttribute("package") Package pack,
                         @RequestParam("users") long newUserId,
                         @RequestParam("status") Status status,
                         BindingResult result,
                         Authentication authentication) {
        if (result.hasErrors()) {
            return "create_package";
        }

        User user = userService.findByEmail(authentication.getName());
        if (userId != user.getId() && !user.getRole().equals(Role.ADMIN) ) {
            throw new AccessDeniedException(String.format("Access for '%s' denied", user.getId()));
        }

        User addresser = userService.readById(userId);
        User recipient = userService.readById(newUserId);

        pack.setAddresser(addresser);
        pack.setRecipient(recipient);
        pack.setStatus(status);

        packageService.create(pack);
        return "redirect:/packages/" + userId + "/all";
    }

    @GetMapping("/{user_id}/read/{pack_id}")
    public String read(@PathVariable("user_id") long userId,
                       @PathVariable("pack_id") long packId,
                       Model model,
                       Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        if (userId != user.getId() && !user.getRole().equals(Role.ADMIN) ) {
            throw new AccessDeniedException(String.format("Access for '%s' denied", user.getId()));
        }
        Package pack = user.getPackages().stream()
                .filter(p -> p.getId() == packId).findFirst().orElseThrow(() -> new RuntimeException("Pack not found!"));
        model.addAttribute("package", pack);
        model.addAttribute("status", pack.getStatus());
        return "read_package";
    }

    @GetMapping("/{user_id}/update/{pack_id}")
    public String update(@PathVariable("user_id") long userId,
                         @PathVariable("pack_id") long packId,
                         Model model,
                         Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        if (userId != user.getId() && !user.getRole().equals(Role.ADMIN) ) {
            throw new AccessDeniedException(String.format("Access for '%s' denied", user.getId()));
        }
        Package pack = user.getPackages().stream()
                .filter(p -> p.getId() == packId).findFirst().orElseThrow(() -> new RuntimeException("Pack not found!"));

        List<User> recipients = userService.getAll();

        model.addAttribute("package", pack);
        model.addAttribute("status", Status.values());
        model.addAttribute("user", user);
        model.addAttribute("users", recipients);
        return "update_package";
    }

    @PostMapping("/{user_id}/update/{pack_id}")
    public String update(@PathVariable("user_id") long userId,
                         @PathVariable("pack_id") long packId,
                         @RequestParam("status") Status status,
                         @RequestParam("users") long newUserId,
                         @Validated @ModelAttribute("package") Package pack,
                         Authentication authentication) {

        Package oldPack = packageService.readById(packId);
        User recipient = userService.findByEmail(authentication.getName());

        if (userId != recipient.getId() && !recipient.getRole().equals(Role.ADMIN) ) {
            throw new AccessDeniedException(String.format("Access for '%s' denied", recipient.getId()));
        }

        pack.setAddresser(oldPack.getAddresser());
        pack.setRecipient(recipient);
        pack.setStatus(status);
        pack.setId(oldPack.getId());
        packageService.update(pack);
        return "redirect:/packages/" + userId + "/all";
    }

    @GetMapping("/{user_id}/delete/{pack_id}")
    public String delete(@PathVariable("user_id") long userId,
                         @PathVariable("pack_id") long packId,
                         Model model,
                         Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        if (userId != user.getId() && !user.getRole().equals(Role.ADMIN) ) {
            throw new AccessDeniedException(String.format("Access for '%s' denied", user.getId()));
        }
        List<Package> packages = user.getPackages();
        model.addAttribute("packages", packages);
        packageService.delete(packId);
        return "redirect:/packages/" + userId + "/all";
    }

    @GetMapping("/{user_id}/all")
    @PreAuthorize("authentication.principal.id == #userId")
    public String getAll(@PathVariable("user_id") long userId,
                         Model model,
                         Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        if (userId != user.getId() && !user.getRole().equals(Role.ADMIN) ) {
            throw new AccessDeniedException(String.format("Access for '%s' denied", user.getId()));
        }
        List<Package> packages = user.getPackages();
        model.addAttribute("packages", packages);
        model.addAttribute("user", user.getFirstName() + " " + user.getLastName());
        return "user_packages";
    }
}
