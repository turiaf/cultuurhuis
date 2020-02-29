package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.dto.MandjeLijn;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.sessions.Mandje;
import be.vdab.cultuurhuis.sessions.StateMandje;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final VoorstellingService voorstellingService;
    private final StateMandje stateMandje;

    public MandjeController(Mandje mandje, VoorstellingService voorstellingService, StateMandje stateMandje) {
        this.mandje = mandje;
        this.voorstellingService = voorstellingService;
        this.stateMandje = stateMandje;
    }

    @GetMapping
    public ModelAndView toonMandje() {
        ModelAndView modelAndView = new ModelAndView("mandje");
        if (mandje.isGevuld()) {
            List<MandjeLijn> mandjeList = new ArrayList<>();
            List<Long> idList = new ArrayList<Long>(mandje.getVoorstellingen().keySet());
            List<Voorstelling> voorstellingList = voorstellingService.findAllInList(idList);
            mandje.getVoorstellingen().entrySet().stream().forEach(entry -> {
                voorstellingList.stream().forEach(voorstelling -> {
                    if(entry.getKey() == voorstelling.getId()) {
                        BigDecimal teBetalen = voorstelling.teBetalen(entry.getValue());
                        mandjeList.add(new MandjeLijn(voorstelling, entry.getValue()));
                    }
                });
            });
            modelAndView.addObject("mandjeList", mandjeList)
                    .addObject("totaal", mandje.getTotaal());
        } else {
            modelAndView.addObject("totaal", mandje.getTotaal());
        }
        return modelAndView;
    }

    @PostMapping("verwijderen")
    public ModelAndView verwijderen(@RequestParam("ver") List<Long> idList) {
        List<Voorstelling> voorstellingList = voorstellingService.findAllInList(idList);
        voorstellingList.stream().forEach(voorstelling -> {
            mandje.verlaagTotaal(voorstelling.teBetalen(
                    mandje.getVoorstellingen().get(voorstelling.getId())));
            mandje.deleteItem(voorstelling.getId());
        });
        if(mandje.isGevuld()) {
            stateMandje.setGevuld(true);
        } else {
            stateMandje.setGevuld(false);
        }
        return new ModelAndView("redirect:/mandje");
    }
}
