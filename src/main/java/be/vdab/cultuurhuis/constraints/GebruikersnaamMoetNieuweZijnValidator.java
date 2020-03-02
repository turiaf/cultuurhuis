package be.vdab.cultuurhuis.constraints;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.forms.NieuweKlantForm;
import be.vdab.cultuurhuis.repositories.KlantRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class GebruikersnaamMoetNieuweZijnValidator implements ConstraintValidator<GebruikersnaamMoetNieuweZijn, NieuweKlantForm> {
    private final KlantRepository klantRepository;

    public GebruikersnaamMoetNieuweZijnValidator(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    @Override
    public void initialize(GebruikersnaamMoetNieuweZijn gebruikersnaamMoetNieuweZijn) {

    }

    @Override
    public boolean isValid(NieuweKlantForm form, ConstraintValidatorContext context) {
        if(form.getGebruikersnaam() == null) {
            return true;
        }
        Optional<Klant> optionalKlant = klantRepository.findByGebruikersnaamEquals(form.getGebruikersnaam());
        return optionalKlant.isPresent() ? false : true;
    }
}
