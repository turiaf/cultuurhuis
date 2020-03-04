package be.vdab.cultuurhuis.domain;

import be.vdab.cultuurhuis.exceptions.NietGenoegPlaatsException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "voorstellingen")
public class Voorstelling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Positive
    private Long id;
    private String titel;
    private String uitvoerders;
    @DateTimeFormat(style = "SS")
    private LocalDateTime datum;
    @NumberFormat(pattern = "0.00")
    private BigDecimal prijs;
    private long vrijeplaatsen;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "genreid")
    private Genre genre;
    @Version
    private long versie;

    public Long getId() {
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

    public Genre getGenre() {
        return genre;
    }

    public BigDecimal teBetalen(long aantal) {
        if(aantal <= 0) {
            throw new IllegalArgumentException();
        }
        return prijs.multiply(BigDecimal.valueOf(aantal)).setScale(2, RoundingMode.HALF_UP);
    }

    public void verlagenPlaats(long aantal) {
        if(vrijeplaatsen < aantal) {
            throw new NietGenoegPlaatsException();
        }
        vrijeplaatsen = vrijeplaatsen - aantal;
    }

}
