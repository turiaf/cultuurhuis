package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.forms.PlaatsenForm;
import be.vdab.cultuurhuis.services.GenreService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("genres")
class VoorstellingController {
    private final GenreService genreService;
    private final VoorstellingService voorstellingService;

    public VoorstellingController(GenreService genreService, VoorstellingService voorstellingService) {
        this.genreService = genreService;
        this.voorstellingService = voorstellingService;
    }

    @GetMapping("{optionalGenre}/voorstellingen")
    public ModelAndView toonVoorstellingen(@PathVariable Optional<Genre> optionalGenre) {
        ModelAndView modelAndView = new ModelAndView("voorstellingen");
        modelAndView.addObject("genres", genreService.findAllOrderNaam());
        optionalGenre.ifPresent(genre -> modelAndView.addObject("voorstellingen",
                voorstellingService.findByGenreZonderVerleden(genre.getId()))
                .addObject("genre", genre));
        return modelAndView;
    }

    @GetMapping("voorstelling/{optionalVoorstelling}/reserveren")
    public ModelAndView toonReservatie(@PathVariable Optional<Voorstelling> optionalVoorstelling) {
        ModelAndView modelAndView = new ModelAndView("reserveren");
        optionalVoorstelling.ifPresent(voorstelling -> {
            modelAndView.addObject("voorstelling", voorstelling)
                    .addObject(new PlaatsenForm(voorstelling,null));
        });
        return modelAndView;
    }

}
