package be.vdab.cultuurhuis.forms;

import be.vdab.cultuurhuis.constraints.Plaatsen;
import be.vdab.cultuurhuis.domain.Voorstelling;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Plaatsen
public class PlaatsenForm {
    @Valid
    private final Voorstelling voorstelling;
    @NotNull
    @Positive
    private Long plaatsen;

    public PlaatsenForm(Voorstelling voorstelling, Long plaatsen) {
        this.voorstelling = voorstelling;
        this.plaatsen = plaatsen;
    }

    public Voorstelling getVoorstelling() {
        return voorstelling;
    }

    public Long getPlaatsen() {
        return plaatsen;
    }

    public void setPlaatsen(Long plaatsen) {
        this.plaatsen = plaatsen;
    }
}
