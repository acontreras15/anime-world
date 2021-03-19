package com.mangaworld.animeworld.controllers;

import com.mangaworld.animeworld.data.AnimeRepository;
import com.mangaworld.animeworld.models.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/add")
public class AnimeController {

    private AnimeRepository animeRepo;

    @Autowired
    public AnimeController(AnimeRepository animeRepo){
        this.animeRepo = animeRepo;
    }

    @GetMapping
    public String showAnimeForm(Model model){
        model.addAttribute("anime", new Anime());
        return "add-anime";
    }

    @PostMapping
    public String handleAnimeForm(@Valid @ModelAttribute("anime") Anime anime, Errors errors) {
        if(errors.hasErrors())
            return "add-anime";
        this.animeRepo.save(anime);
        return "redirect:/view-anime";
    }
}
