package com.mangaworld.animeworld.controllers;

import com.mangaworld.animeworld.data.MangaRepository;
import com.mangaworld.animeworld.models.Manga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manga")
public class MangaController {

    private MangaRepository mangaRepo;

    @Autowired
    public MangaController(MangaRepository mangaRepo){
        this.mangaRepo = mangaRepo;
    }

    @RequestMapping("/view")
    public String showManga(Model model){
        List<Manga> manga = (List<Manga>) this.mangaRepo.findAll();
        model.addAttribute("manga", manga);
        return "display-manga";
    }

}
