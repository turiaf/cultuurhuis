package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.sessions.StateMandje;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
class MyControllerAdvice {
    private final StateMandje stateMandje;

    public MyControllerAdvice(StateMandje stateMandje) {
        this.stateMandje = stateMandje;
    }
    @ModelAttribute
    void extraDataToevoegenAanModel(Model model) {
        model.addAttribute(stateMandje);
    }
}
