package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("genres")
class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("{genre}")
    public ModelAndView toonGenreDetails(@PathVariable Optional<Genre> genre) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("genres", genreService.findAllOrderNaam());
        genre.ifPresent(genre1 -> modelAndView.addObject("genre", genre1));
        return modelAndView;
    }
}
