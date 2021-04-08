package com.mangaworld.animeworld.controllers;

import com.mangaworld.animeworld.data.AnimeRepository;
import com.mangaworld.animeworld.data.WeeklyRepository;
import com.mangaworld.animeworld.models.Anime;
import com.mangaworld.animeworld.models.Weekly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/anime")
public class AnimeController {

    private AnimeRepository animeRepo;
    private WeeklyRepository weeklyRepo;

    @Autowired
    public AnimeController(AnimeRepository animeRepo, WeeklyRepository weeklyRepo){
        this.animeRepo = animeRepo;
        this.weeklyRepo = weeklyRepo;
    }

    @GetMapping("/add")
    public String showAnimeForm(Model model){
        List<Weekly> weeklyA = (List<Weekly>) weeklyRepo.findAll();
        model.addAttribute("weeklyA", weeklyA);
        model.addAttribute("anime", new Anime());
        return "add-anime";
    }

    @PostMapping("/add")
    public String handleAnimeForm(@Valid @ModelAttribute("anime") Anime anime, Errors errors) {
        if(errors.hasErrors())
            return "add-anime";

        try{
            this.animeRepo.save(anime);
        }catch(DataIntegrityViolationException e){
            errors.rejectValue("animeName", "Anime already stored", "Please add a different anime");
            return "add-anime";
        }

        return "redirect:/anime/view";
    }

    @GetMapping("/view/{id}")
    public String showAnime(@PathVariable Long id, Model model){
        Anime anime = this.animeRepo.findById(id).get();
        model.addAttribute("anime", anime);
        return "view-anime";
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

        return "redirect:/anime/view";
    }

    private void updateOriginalAnime(Anime original, Anime update){
        original.setAnimeName(update.getAnimeName());
        original.setDescription(update.getDescription());
        original.setGenre(update.getGenre());
        original.setEpisodes(update.getEpisodes());

    }

    @GetMapping("/delete/{id}")
    public String deleteAnime(@PathVariable Long id){
        System.out.println(id);
        this.animeRepo.deleteById(id);
        return "redirect:/anime/view";
    }

   @GetMapping("/{id}/delete-weekly/{weeklyId}")
    public String deleteWeekly(@PathVariable Long id, @PathVariable Long weeklyId, Model model){
        Anime originalAnime = this.animeRepo.findById(id).get();
        Weekly weekly = this.weeklyRepo.findById(weeklyId).get();
        originalAnime.getWeeklyAnime().remove(weekly);
        this.animeRepo.save(originalAnime);
        return "redirect:/anime/view/" + id;
   }
}
