package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Adres;
import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.forms.NieuweKlantForm;
import be.vdab.cultuurhuis.services.KlantService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("klant")
class KlantController {
    private final KlantService klantService;

    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    @PostMapping
    public ModelAndView nieuweKlant() {
        ModelAndView modelAndView = new ModelAndView("klant");
        modelAndView.addObject(new NieuweKlantForm(null,null,
                new Adres(null,null,null, null),null,null,null));
        return modelAndView;
    }

    @PostMapping("toevoegen")
    public ModelAndView klantToevoegen(@Valid NieuweKlantForm nieuweKlantForm, Errors errors, BindingResult bindingResult) {
        if(errors.hasErrors()) {
            return new ModelAndView("klant");
        }
        Klant klant = nieuweKlantForm;
        klantService.klantToevoegen(klant);
        return new ModelAndView("redirect:/reservaties/bevestigen");
    }
    @InitBinder("nieuweKlantForm")
    void initBinder(DataBinder binder) {
        binder.initDirectFieldAccess();
    }
}
