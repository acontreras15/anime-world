package com.mangaworld.animeworld.controllers;


import com.mangaworld.animeworld.data.MangaRepository;
import com.mangaworld.animeworld.models.Anime;
import com.mangaworld.animeworld.models.Manga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/mangas")
public class AddMangaController {

    private MangaRepository mangaRepo;

    @Autowired
    public AddMangaController(MangaRepository mangaRepo){
        this.mangaRepo = mangaRepo;
    }

    @GetMapping("/add")
    public String showMangaForm(Model model){
        model.addAttribute("manga", new Manga());
        return "add-manga";
    }

    @PostMapping("/add")
    public String handleMangaForm(@Valid @ModelAttribute("manga") Manga manga, Errors errors) {
        if(errors.hasErrors())
            return "add-manga";

        try {
            this.mangaRepo.save(manga);
        } catch(DataIntegrityViolationException e){
            errors.rejectValue("mangaName", "Manga error", "Manga already exist");
            return "add-manga";
        }

        return "redirect:/manga/view";
    }
}
