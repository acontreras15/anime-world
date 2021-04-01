package com.mangaworld.animeworld.controllers;

import com.mangaworld.animeworld.data.GenreRepository;
import com.mangaworld.animeworld.models.AnimeGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/genre")
public class AnimeGenreController {

    private GenreRepository genreRepo;

    @Autowired
    public AnimeGenreController(GenreRepository genreRepo){
        this.genreRepo = genreRepo;
    }

    @RequestMapping("/view")
    public String showGenreAnime(Model model){
        List<AnimeGenre> genreA = (List<AnimeGenre>) this.genreRepo.findAll();
        model.addAttribute("genreA", genreA);
        return "display-genre";
    }
}
