package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.exceptions.VoorstellingNietGevondenException;
import be.vdab.cultuurhuis.forms.PlaatsenForm;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.sessions.Mandje;
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
@RequestMapping("reservaties")
public class MandjeController {
    private final Mandje mandje;
    private final VoorstellingService voorstellingService;

    public MandjeController(Mandje mandje, VoorstellingService voorstellingService) {
        this.mandje = mandje;
        this.voorstellingService = voorstellingService;
    }

    @PostMapping("{optionalVoorstelling}")
    public ModelAndView reserveren(@Valid PlaatsenForm form, Errors errors,
                                   @PathVariable Optional<Voorstelling> optionalVoorstelling) {
        if(errors.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("reserveren");
            optionalVoorstelling.ifPresent(voorstelling -> {
                modelAndView.addObject("voorstelling", voorstelling);
            });
            return modelAndView;
        }
        if(optionalVoorstelling.isPresent()) {
            Voorstelling voorstelling = optionalVoorstelling.get();
            long plaats = mandje.addVoorstelling(voorstelling.getId(), form.getPlaatsen());
            if(plaats == 0 || plaats == -1) {
                mandje.verhoogTotaal(voorstelling.teBetalen(form.getPlaatsen()));
                return new ModelAndView("redirect:/");
            } else {
                ModelAndView modelAndView = new ModelAndView("reserveren");
                modelAndView.addObject("voorstelling", voorstelling);
                form.setPlaatsen(plaats);
                modelAndView.addObject("plaatsenForm", form)
                .addObject("keer", true);
                return modelAndView;
            }
        } else {
            return new ModelAndView("voorstellingen");
        }
    }
}
