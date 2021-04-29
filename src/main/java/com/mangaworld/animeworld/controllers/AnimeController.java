package com.mangaworld.animeworld.controllers;

import com.mangaworld.animeworld.data.AnimeRepository;
import com.mangaworld.animeworld.data.MangaRepository;
import com.mangaworld.animeworld.models.Anime;
import com.mangaworld.animeworld.models.User;
import com.mangaworld.animeworld.models.Manga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/anime")
public class AnimeController {

    private AnimeRepository animeRepo;
    private MangaRepository mangaRepo;

    @Autowired
    public AnimeController(AnimeRepository animeRepo, MangaRepository mangaRepo){
        this.animeRepo = animeRepo;
        this.mangaRepo = mangaRepo;
    }

    @GetMapping("/add")
    public String showAnimeForm(Model model){
        List<Manga> mangas = (List<Manga>) mangaRepo.findAll();
        model.addAttribute("mangas", mangas);
        model.addAttribute("anime", new Anime());
        return "add-anime";
    }

    @PostMapping("/add")
    public String handleAnimeForm(@Valid @ModelAttribute("anime") Anime anime, Errors errors, @AuthenticationPrincipal User user) {
        if(errors.hasErrors())
            return "add-anime";

        try {
            anime.setAnimeName(anime.getManga().getMangaName());
            anime.setUser(user);
            this.animeRepo.save(anime);
        } catch(DataIntegrityViolationException e){
            System.out.println();
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
        System.out.println(anime.getAnimeName());
        System.out.println(anime.getManga());
        System.out.println(anime.getDescription());
        System.out.println(anime.getEpisodes());
        System.out.println(anime.getGenre());
        System.out.println(anime.getUser());
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

    private void updateOriginalAnime(Anime original, Anime update) {
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

}
