package be.vdab.cultuurhuis.domain;

import javax.persistence.*;

@Entity
@Table(name = "klanten")
public class Klant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String voornaam;
    private String familienaam;
    @Embedded
    private Adres adres;
    private String gebruikernaam;
    private String paswoord;

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

    public String getGebruikernaam() {
        return gebruikernaam;
    }

    public String getPaswoord() {
        return paswoord;
    }
}