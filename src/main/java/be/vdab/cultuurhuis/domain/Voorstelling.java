package be.vdab.cultuurhuis.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "voorstellingen")
public class Voorstelling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titel;
    private String uitvoerders;
    private LocalDateTime datum;
    private BigDecimal prijs;
    private long vrijeplaatsen;
    @Version
    private long versie;

    public long getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getUitvoerders() {
        return uitvoerders;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public long getVrijeplaatsen() {
        return vrijeplaatsen;
    }
}
