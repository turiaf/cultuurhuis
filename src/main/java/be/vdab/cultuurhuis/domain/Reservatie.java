package be.vdab.cultuurhuis.domain;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "reservaties")
public class Reservatie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long plaatsen;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "klantid")
    private Klant klant;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voorstellingid")
    private Voorstelling voorstelling;

    protected Reservatie() {
    }

    public Reservatie(long plaatsen, Klant klant, Voorstelling voorstelling) {
        this.plaatsen = plaatsen;
        this.klant = klant;
        this.voorstelling = voorstelling;
    }

    public long getId() {
        return id;
    }

    public long getPlaatsen() {
        return plaatsen;
    }

    public Klant getKlant() {
        return klant;
    }

    public Voorstelling getVoorstelling() {
        return voorstelling;
    }
}
