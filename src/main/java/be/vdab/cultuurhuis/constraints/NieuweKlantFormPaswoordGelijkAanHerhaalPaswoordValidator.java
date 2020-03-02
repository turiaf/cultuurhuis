package be.vdab.cultuurhuis.constraints;

import be.vdab.cultuurhuis.forms.NieuweKlantForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NieuweKlantFormPaswoordGelijkAanHerhaalPaswoordValidator implements ConstraintValidator<KlantDeZelfdePaswoord, NieuweKlantForm> {
    @Override
    public void initialize(KlantDeZelfdePaswoord klantDeZelfdePaswoord) {

    }

    @Override
    public boolean isValid(NieuweKlantForm form, ConstraintValidatorContext context) {
        if(form.getPaswoord() == null || form.getHerhaalPaswoord() == null) {
            return true;
        }
        return form.getPaswoord().equals(form.getHerhaalPaswoord());
    }
}
