package be.vdab.cultuurhuis.dto;

import be.vdab.cultuurhuis.domain.Voorstelling;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GeluktMislukte {
    private List<OverzichtLijn> gelukte = new LinkedList<>();
    private List<OverzichtLijn> mislukte = new LinkedList<>();

    public void addGelukte(Voorstelling voorstelling, long plaatsen) {
        OverzichtLijn overzichtLijn = new OverzichtLijn(voorstelling, plaatsen);
        gelukte.add(overzichtLijn);
    }
    public void addMislukte(Voorstelling voorstelling, long plaatsen) {
        OverzichtLijn overzichtLijn = new OverzichtLijn(voorstelling, plaatsen);
        mislukte.add(overzichtLijn);
    }

    public List<OverzichtLijn> getGelukte() {
        return Collections.unmodifiableList(gelukte);
    }

    public List<OverzichtLijn> getMislukte() {
        return Collections.unmodifiableList(mislukte);
    }
}
