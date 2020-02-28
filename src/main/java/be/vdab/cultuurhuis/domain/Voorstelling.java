package be.vdab.cultuurhuis.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "voorstellingen")
public class Voorstelling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titel;
    private String uitvoerders;
    @DateTimeFormat
    private LocalDateTime datum;
    @NumberFormat(pattern = "0.00")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voorstelling)) return false;
        Voorstelling that = (Voorstelling) o;
        return Objects.equals(titel, that.titel) &&
                Objects.equals(datum, that.datum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titel, datum);
    }
}
