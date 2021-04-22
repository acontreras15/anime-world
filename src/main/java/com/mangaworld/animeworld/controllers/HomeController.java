package com.mangaworld.animeworld.controllers;

import com.mangaworld.animeworld.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHomePage(Model modal, @AuthenticationPrincipal User user){
        modal.addAttribute("user", user);
        return "anime-world";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    //@GetMapping("/sign-up")
    //public String getSignUpPage(){
     //  return "add-user";
    //}
}
