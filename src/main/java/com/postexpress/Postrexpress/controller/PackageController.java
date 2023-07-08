package com.postexpress.Postrexpress.controller;

import com.postexpress.Postrexpress.model.Package;
import com.postexpress.Postrexpress.model.User;
import com.postexpress.Postrexpress.service.PackageService;
import com.postexpress.Postrexpress.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
                         Model model, Authentication authentication){
        User authUser = userService.findByEmail(authentication.getName());
        if (userId != authUser.getId() ) {
            throw new AccessDeniedException("Access Denied");
        }
        model.addAttribute("package", new Package());
        model.addAttribute("user_id", userId);
        return "create_package";

    }

    @PostMapping("/{user_id}/create")
    public String create(@PathVariable("user_id") long userId,
                         @Validated @ModelAttribute("package") Package pack,
                         BindingResult result,
                         Authentication authentication) {
        User authUser = userService.findByEmail(authentication.getName());
        if (userId != authUser.getId() ) {
            throw new AccessDeniedException("Access Denied");
        }

        if(result.hasErrors()){
            return "create_package";
        }

        pack.setAddresser(userService.readById(userId));
        packageService.create(pack);
        return "redirect:/users/packages/" + userId;

    }
}
