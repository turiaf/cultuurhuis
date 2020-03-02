package be.vdab.cultuurhuis.forms;

import be.vdab.cultuurhuis.constraints.GebruikersnaamMoetNieuweZijn;
import be.vdab.cultuurhuis.constraints.KlantDeZelfdePaswoord;
import be.vdab.cultuurhuis.domain.Adres;
import be.vdab.cultuurhuis.domain.Klant;

import javax.validation.constraints.NotBlank;

@KlantDeZelfdePaswoord
@GebruikersnaamMoetNieuweZijn
public class NieuweKlantForm extends Klant{
    @NotBlank
    private String herhaalPaswoord;

    public NieuweKlantForm(String voornaam, String familienaam, Adres adres,
                           String gebruikersnaam, String paswoord, String herhaalPaswoord) {
        super(voornaam, familienaam, adres, gebruikersnaam, paswoord);
        this.herhaalPaswoord = herhaalPaswoord;
    }

    public String getHerhaalPaswoord() {
        return herhaalPaswoord;
    }
}
