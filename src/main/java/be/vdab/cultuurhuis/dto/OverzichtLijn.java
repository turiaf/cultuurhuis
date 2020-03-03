package be.vdab.cultuurhuis.dto;

import be.vdab.cultuurhuis.domain.Voorstelling;

public class OverzichtLijn {
    private Voorstelling voorstelling;
    private long aantal;

    public OverzichtLijn(Voorstelling voorstelling, long aantal) {
        this.voorstelling = voorstelling;
        this.aantal = aantal;
    }

    public Voorstelling getVoorstelling() {
        return voorstelling;
    }

    public long getAantal() {
        return aantal;
    }
}
