package com.mangaworld.animeworld.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view-anime")
public class ViewAnimeController {

    @GetMapping
    public String displayAnime(@ModelAttribute("storedAnime") Object flashAttribute,  Model model) {
        model.addAttribute("storedAnime", flashAttribute);
        return "display-anime";
    }
}
