package com.sparta.jd.pressplay.controllers;

import com.sparta.jd.pressplay.entities.FilmEntity;
import com.sparta.jd.pressplay.repositories.FilmRepository;
import net.bytebuddy.pool.TypePool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FilmController {
    private final FilmRepository FILM_REPOSITORY;

    public FilmController(FilmRepository repository) {this.FILM_REPOSITORY = repository;}

    @GetMapping("/")
    public String goToHomepage(Model model) {
        model.addAttribute("filmData", FILM_REPOSITORY.findAll());
        return "index";
    }

    @PostMapping("/film-search")
    public String searchCustomer(@ModelAttribute("filmName") String filmName, Model model) {
        List<FilmEntity> foundFilms = new ArrayList<>();
        System.out.println(filmName);
        for (FilmEntity entity : FILM_REPOSITORY.findAll()) {
            if (entity.getTitle().toLowerCase().contains(filmName.toLowerCase()))
            {
                foundFilms.add(entity);
            }
        }
        if(foundFilms.size() > 0) {
            model.addAttribute("searchResults", foundFilms);
            return "film-search";
        }
        else {
            return "empty-search";
        }
    }

    @GetMapping("/film/{id}")
    public String goToIndividualFilmPage(@PathVariable("id") Integer id, Model model) {
        System.out.println("Going to individual film page");
        FilmEntity filmEntity = FILM_REPOSITORY.findById(id).orElseThrow(() -> new IllegalArgumentException("No Film Found By ID:" + id));
        model.addAttribute("filmData", filmEntity);
        return "film";
    }

    @GetMapping("/rent/{id}")
    public String goToRentFilmPage(@PathVariable("id") Integer id, Model model) {
        System.out.println("Going to rent film page");
        FilmEntity filmEntity = FILM_REPOSITORY.findById(id).orElseThrow(() -> new IllegalArgumentException("No Film Found By ID:" + id));
        model.addAttribute("filmData", filmEntity);
        return "rent";
    }
}
