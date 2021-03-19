package com.mangaworld.animeworld.controllers;

import com.mangaworld.animeworld.data.AnimeRepository;
import com.mangaworld.animeworld.models.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/view-anime")
public class ViewAnimeController {

    private AnimeRepository animeRepo;

    @Autowired
    public ViewAnimeController(AnimeRepository animeRepo){
        this.animeRepo = animeRepo;
    }

    @GetMapping
    public String displayAnime(Model model) {
        List<Anime> anime = (List<Anime>) this.animeRepo.findAll();
        model.addAttribute("anime", anime);
        return "display-anime";
    }
}
