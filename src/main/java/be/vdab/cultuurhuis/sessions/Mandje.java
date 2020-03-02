package be.vdab.cultuurhuis.sessions;

import be.vdab.cultuurhuis.domain.Voorstelling;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Map<Long, Long> voorstellingen = new HashMap<>();
    private final Map<Long, Boolean> keer = new HashMap<>();
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal totaal = BigDecimal.ZERO;

    public long addVoorstelling(long id, long aantal) {
        if (!voorstellingen.containsKey(id)) {
            voorstellingen.put(id, aantal);
            keer.put(id, false);
            return 0;
        } else if (keer.get(id) == false) {
            keer.replace(id, true);
            return voorstellingen.get(id);
        } else {
            keer.replace(id, false);
            return -1;
        }
    }

    public boolean isGevuld() {
        return !voorstellingen.isEmpty();
    }

    public void verhoogTotaal(BigDecimal prijs) {
        totaal = totaal.add(prijs);
    }

    public void verlaagTotaal(BigDecimal prijs) {
        totaal = totaal.subtract(prijs);
    }

    public Map<Long, Long> getVoorstellingen() {
        return voorstellingen;
    }

    public BigDecimal getTotaal() {
        return totaal;
    }

    public void addTotaalAfterWijzijgen(Voorstelling voorstelling, long aantal) {
        totaal = totaal.subtract(voorstelling.teBetalen(voorstellingen.get(voorstelling.getId())));
        voorstellingen.replace(voorstelling.getId(), aantal);
        totaal = totaal.add(voorstelling.teBetalen(aantal));
    }

    public void deleteItem(long id) {
        voorstellingen.remove(id);
        keer.remove(id);
    }

    public void deleteMandje() {
        voorstellingen.clear();
        totaal = BigDecimal.ZERO;
    }
}
