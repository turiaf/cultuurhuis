package be.vdab.cultuurhuis.dto;

import be.vdab.cultuurhuis.domain.Voorstelling;

public class MandjeLijn {
    private final Voorstelling voorstelling;
    private final Long plaatsen;

    public MandjeLijn(Voorstelling voorstelling, Long plaatsen) {
        this.voorstelling = voorstelling;
        this.plaatsen = plaatsen;
    }

    public Voorstelling getVoorstelling() {
        return voorstelling;
    }

    public Long getPlaatsen() {
        return plaatsen;
    }
}
