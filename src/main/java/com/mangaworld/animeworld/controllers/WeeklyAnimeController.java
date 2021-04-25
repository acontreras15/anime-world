package com.mangaworld.animeworld.controllers;

import com.mangaworld.animeworld.data.WeeklyRepository;
import com.mangaworld.animeworld.models.User;
import com.mangaworld.animeworld.models.Weekly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/weekly")
public class WeeklyAnimeController {

    private WeeklyRepository weeklyRepo;

    @Autowired
    public WeeklyAnimeController(WeeklyRepository weeklyRepo){
        this.weeklyRepo = weeklyRepo;
    }

    @RequestMapping("/view")
    public String showWeeklyAnime(Model model){
        List<Weekly> weeklyA = (List<Weekly>) this.weeklyRepo.findAll();
        model.addAttribute("weeklyA", weeklyA);
        return "display-weekly";
    }
}
