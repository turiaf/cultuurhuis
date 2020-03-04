package be.vdab.cultuurhuis.domain;

import be.vdab.cultuurhuis.constraints.NaamZonderHtml;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "klanten")
public class Klant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @NaamZonderHtml
    private String voornaam;
    @NotBlank
    @NaamZonderHtml
    private String familienaam;
    @Valid
    @Embedded
    private Adres adres;
    @NotBlank
    @NaamZonderHtml
    private String gebruikersnaam;
    @NotBlank
    @NaamZonderHtml
    private String paswoord;

    protected Klant() {
    }
    public Klant(String voornaam, String familienaam,
                 Adres adres, String gebruikersnaam, String paswoord) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.adres = adres;
        this.gebruikersnaam = gebruikersnaam;
        this.paswoord = paswoord;
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public Adres getAdres() {
        return adres;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public void encryptPaswoord() {
        paswoord = new BCryptPasswordEncoder().encode(this.paswoord);
    }
}
