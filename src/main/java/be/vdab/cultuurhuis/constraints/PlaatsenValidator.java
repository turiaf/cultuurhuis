package be.vdab.cultuurhuis.constraints;

import be.vdab.cultuurhuis.forms.PlaatsenForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PlaatsenValidator implements ConstraintValidator<Plaatsen, PlaatsenForm> {
    @Override
    public void initialize(Plaatsen plaatsen) {

    }

    @Override
    public boolean isValid(PlaatsenForm form, ConstraintValidatorContext context) {
        if(form.getVoorstelling() == null || form.getPlaatsen() == null) {
            return true;
        }
        return form.getVoorstelling().getVrijeplaatsen() >= form.getPlaatsen();
    }
}
