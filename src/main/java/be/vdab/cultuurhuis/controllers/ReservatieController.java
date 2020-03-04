package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.dto.GeluktMislukte;
import be.vdab.cultuurhuis.exceptions.VoorstellingNietGevondenException;
import be.vdab.cultuurhuis.forms.PlaatsenForm;
import be.vdab.cultuurhuis.services.KlantService;
import be.vdab.cultuurhuis.services.ReservatieService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.sessions.Mandje;
import be.vdab.cultuurhuis.sessions.StateMandje;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("reservaties")
public class ReservatieController {
    private final Mandje mandje;
    private final VoorstellingService voorstellingService;
    private final StateMandje stateMandje;
    private final KlantService klantService;
    private final ReservatieService reservatieService;

    public ReservatieController(Mandje mandje, VoorstellingService voorstellingService,
                                StateMandje stateMandje, KlantService klantService,
                                ReservatieService reservatieService) {
        this.mandje = mandje;
        this.voorstellingService = voorstellingService;
        this.stateMandje = stateMandje;
        this.klantService = klantService;
        this.reservatieService = reservatieService;
    }

    @PostMapping("{optionalVoorstelling}")
    public ModelAndView reserveren(@Valid PlaatsenForm form, Errors errors,
                                   @PathVariable Optional<Voorstelling> optionalVoorstelling) {
        if (errors.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("reserveren");
            optionalVoorstelling.ifPresent(voorstelling -> {
                modelAndView.addObject("voorstelling", voorstelling);
            });
            return modelAndView;
        }
        if (optionalVoorstelling.isPresent()) {
            Voorstelling voorstelling = optionalVoorstelling.get();
            long plaats = mandje.addVoorstelling(voorstelling.getId(), form.getPlaatsen());
            if (plaats == 0) {
                mandje.verhoogTotaal(voorstelling.teBetalen(form.getPlaatsen()));
                if (!stateMandje.isGevuld()) {
                    stateMandje.setGevuld(true);
                }
                return new ModelAndView("redirect:/mandje");
            } else if (plaats == -1) {
                mandje.addTotaalAfterWijzijgen(voorstelling, form.getPlaatsen());
                return new ModelAndView("redirect:/mandje");
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

    @GetMapping("bevestigen")
    public ModelAndView bevestigen() {
        if (mandje.isGevuld()) {
            ModelAndView modelAndView = new ModelAndView("bevestigen");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String nam = authentication.getName();
            Optional<Klant> optionalKlant = klantService.findByGebruikersnaamEquals(nam);
            optionalKlant.ifPresent(klant -> {
                modelAndView.addObject("klant", klant);
            });
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/");
        }

    }

    @PostMapping("bevestigen")
    public ModelAndView confirm(@RequestParam("id") Klant klant, RedirectAttributes redirect) {
        ModelAndView modelAndView = new ModelAndView("overzicht");
        if (mandje.isGevuld()) {
            try {
                GeluktMislukte geluktMislukte = reservatieService.confirmReservatie(mandje.getVoorstellingen(), klant);
                modelAndView.addObject("misluktes", geluktMislukte.getMislukte());
                modelAndView.addObject("geluktes", geluktMislukte.getGelukte());
            } catch (ObjectOptimisticLockingFailureException | InterruptedException e) {
                redirect.addAttribute("fout", true);
                return new ModelAndView("redirect:/reservaties/bevestigen");
            } catch (VoorstellingNietGevondenException ex) {
                mandje.deleteMandje();
                stateMandje.setGevuld(false);
                return new ModelAndView("mandjefeedback", "voorstellingVerwijde", true);
            }
        } else {
//            return new ModelAndView("redirect:/reservaties/bevestigen");
            return new ModelAndView("mandjefeedback", "mandjeLeeg", true);
        }
        mandje.deleteMandje();
        stateMandje.setGevuld(false);
        return modelAndView;
    }
}
