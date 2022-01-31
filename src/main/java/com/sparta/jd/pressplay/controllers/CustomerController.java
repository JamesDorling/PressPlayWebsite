package com.sparta.jd.pressplay.controllers;

import com.sparta.jd.pressplay.entities.FilmEntity;
import com.sparta.jd.pressplay.entities.RentalEntity;
import com.sparta.jd.pressplay.entities.ReservedEntity;
import com.sparta.jd.pressplay.entities.StoreAddressEntity;
import com.sparta.jd.pressplay.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class CustomerController {
    private final CustomerRepository CUSTOMER_REPOSITORY;
    private final FilmRepository FILM_REPOSITORY;
    private final StoreAddressRepository STORE_REPOSITORY;
    private final RentalRepository RENTAL_REPOSITORY;
    private final ReservedRepository RESERVED_REPOSITORY;

    public CustomerController(FilmRepository filmRepository, StoreAddressRepository storeRepository,
                              RentalRepository rentalRepository, CustomerRepository customerRepository,
                              ReservedRepository reservedRepository) {
        this.CUSTOMER_REPOSITORY = customerRepository;
        this.FILM_REPOSITORY = filmRepository;
        this.STORE_REPOSITORY = storeRepository;
        this.RENTAL_REPOSITORY = rentalRepository;
        this.RESERVED_REPOSITORY = reservedRepository;
    }

    @GetMapping("/login")
    public String goToLoginPage() {return "login";}

    @GetMapping("/error")
    public String goToErrorPage() {return "error";}

    @GetMapping("/access-denied")
    public String goToAccessDenied() {return "access-denied";}

    @GetMapping("/about-us")
    public String goToAboutUs(Model model) {
        model.addAttribute("locationData", STORE_REPOSITORY.findAll());
        return "about-us";
    }

    @GetMapping("/")
    public String goToHomepage(Model model) {
        List<FilmEntity> films = FILM_REPOSITORY.findAll();
        model.addAttribute("filmData", removeAllUnavailableFilms((films)));
        return "index";
    }



    @PostMapping("/film-search")
    public String searchFilm(@ModelAttribute("filmName") String filmName, Model model) {
        List<FilmEntity> foundFilms = new ArrayList<>();
        System.out.println(filmName);
        for (FilmEntity entity : FILM_REPOSITORY.findAll()) {
            if (entity.getTitle().toLowerCase().contains(filmName.toLowerCase()))
            {
                foundFilms.add(entity);
            }
        }
        if(foundFilms.size() > 0) {
            model.addAttribute("searchResults", removeAllUnavailableFilms(foundFilms));
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
        model.addAttribute("locationData", STORE_REPOSITORY.findAll());
        return "rent";
    }

    @GetMapping("/rent-confirmation/{filmID}/{locationID}")
    public String goToRentConfirmationPage(@PathVariable("filmID") Integer filmID, @PathVariable("locationID") Integer locationID, Model model) {
        FilmEntity filmEntity = FILM_REPOSITORY.findById(filmID)
                .orElseThrow(() -> new IllegalArgumentException("No Film Found By ID:" + filmID));
        model.addAttribute("filmData", filmEntity);

        StoreAddressEntity addressEntity = STORE_REPOSITORY.findById(locationID)
                .orElseThrow(() -> new IllegalArgumentException("No Location Found By ID:" + locationID));
        model.addAttribute("locationData", addressEntity);
        return "rent-confirmation";
    }

    @GetMapping("/thanks/{filmID}/{locationID}")
    public String goToThanksPage(@PathVariable("filmID") Integer filmID, @PathVariable("locationID") Integer locationID) {
        FilmEntity film = FILM_REPOSITORY.findById(filmID).orElseThrow(() -> new InvalidParameterException("No Film Found By ID:" + filmID));
        RESERVED_REPOSITORY.save(new ReservedEntity(locationID, filmID, film.getTitle(), SecurityContextHolder.getContext().getAuthentication().getName()));
        return "thanks";
    }

    /*
    this.locationID = locationID;
        this.filmID = filmID;
        this.filmName = filmName;
        this.customer_name = customer_name;
     */

    private List<FilmEntity> removeRentedFilms(List<FilmEntity> filmEntities) {
        List<RentalEntity> reservedFilms = RENTAL_REPOSITORY.findAll();
        Iterator<FilmEntity> iterator = filmEntities.iterator();
        while(iterator.hasNext()) {
            FilmEntity currentFilm = iterator.next();
            for (RentalEntity rented : reservedFilms) {
                if (currentFilm.getFilmId().equals(rented.getInventoryId()) && rented.getReturnDate() == null) {
                    iterator.remove();
                    break;
                }
            }
        }
        return filmEntities;
    }

    private List<FilmEntity> removeReservedFilms(List<FilmEntity> filmEntities) {
        List<ReservedEntity> reservedFilms = RESERVED_REPOSITORY.findAll();
        Iterator<FilmEntity> iterator = filmEntities.iterator();
        while(iterator.hasNext()) {
            FilmEntity currentFilm = iterator.next();
            for (ReservedEntity rented : reservedFilms) {
                if (currentFilm.getFilmId().equals(rented.getFilmID())) {
                    iterator.remove();
                    break;
                }
            }
        }
        return filmEntities;
    }

    private List<FilmEntity> removeAllUnavailableFilms(List<FilmEntity> filmEntities) {
        return removeReservedFilms(removeRentedFilms(filmEntities));
    }
}
