package be.vdab.cultuurhuis.dto;

import be.vdab.cultuurhuis.domain.Klant;

public class NieuweKlant extends Klant {

    private String gebruikersnaam;
    private String paswoord;
    private String herhaalPaswoord;

    @Override
    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    @Override
    public String getPaswoord() {
        return paswoord;
    }

    public String getHerhaalPaswoord() {
        return herhaalPaswoord;
    }
}
