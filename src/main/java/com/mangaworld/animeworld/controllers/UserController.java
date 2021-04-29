package com.mangaworld.animeworld.controllers;

import com.mangaworld.animeworld.data.UserRepository;
import com.mangaworld.animeworld.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class UserController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping
    public String handleAnimeForm(@Valid @ModelAttribute("user") User user, Errors errors){
        if(errors.hasErrors())
            return "add-user";

        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userRepo.save(user);
        }catch(DataIntegrityViolationException e){
            errors.rejectValue("email", "Email already exist", "Please add a different email");
            errors.rejectValue("username", "Username already exist", "Please add a different username");
            return "add-user";
        }

        return "redirect:/";
    }
}
