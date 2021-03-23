package com.mangaworld.animeworld.controllers;

import com.mangaworld.animeworld.data.AnimeRepository;
import com.mangaworld.animeworld.models.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/view/{id}")
    public String showAnime(@PathVariable Long id, Model model){
        Anime anime = this.animeRepo.findById(id).get();
        model.addAttribute("anime", anime);
        return "view-anime";
    }

    @PostMapping
    public String handleAnimeForm(@Valid @ModelAttribute("anime") Anime anime, Errors errors) {
        if(errors.hasErrors())
            return "add-anime";

        try{
            this.animeRepo.save(anime);
        }catch(DataIntegrityViolationException e){
            errors.rejectValue("animeName", "Anime already stored", "Please add a different anime");
            return "add-anime";
        }

        return "redirect:/view-anime";
    }

    @PostMapping("/edit/{id}")
    public String handleEditAnimeForm(@PathVariable Long id, @Valid @ModelAttribute("anime") Anime anime, Errors errors){
        if(errors.hasErrors())
            return "view-anime";

        try{
            Anime originalAnime = this.animeRepo.findById(id).get();
            updateOriginalAnime(originalAnime, anime);
            this.animeRepo.save(originalAnime);
        }catch(DataIntegrityViolationException e){
            errors.rejectValue("animeName", "Anime already stored", "Please add a different anime");
            return "view-anime";
        }

        return "redirect:/view-anime";
    }

    private void updateOriginalAnime(Anime original, Anime update){
        original.setAnimeName(update.getAnimeName());
        original.setDescription(update.getDescription());
        original.setGenre(update.getGenre());
        original.setEpisodes(update.getEpisodes());

    }

    @GetMapping("/delete/{id}")
    public String deleteAnime(@PathVariable Long id){
        this.animeRepo.deleteById(id);
        return "redirect:/view-anime";
    }
}
