package com.mangaworld.controllers;

import models.Anime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class AnimeController {

    @GetMapping
    public String showAnimeForm(Model model){
        model.addAttribute("anime", new Anime());
        return "add-anime";
    }

    @PostMapping
    public String handleAnimeForm(@ModelAttribute("anime") Anime anime, RedirectAttributes attributes) {
        attributes.addFlashAttribute("storedAnime", anime.getAnimeName() + " " + anime.getDescription() + anime.getGenre() + " " + anime.getEpisodes());
        return "redirect:/view-anime";
    }
}
