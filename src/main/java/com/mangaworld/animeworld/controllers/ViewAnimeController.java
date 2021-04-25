package com.mangaworld.animeworld.controllers;

import com.mangaworld.animeworld.data.AnimeRepository;
import com.mangaworld.animeworld.models.Anime;
import com.mangaworld.animeworld.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/anime/view")
public class ViewAnimeController {

    private AnimeRepository animeRepo;

    @Autowired
    public ViewAnimeController(AnimeRepository animeRepo){
        this.animeRepo = animeRepo;
    }

    @GetMapping
    public String displayAnime(Model model, @AuthenticationPrincipal User user) {
        List<Anime> anime = this.animeRepo.findAnimeByUser(user);
        model.addAttribute("anime", anime);
        return "display-anime";
    }
}
