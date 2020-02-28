package be.vdab.cultuurhuis.domain;

import javax.persistence.*;

@Entity
@Table(name = "reservaties")
public class Reservatie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String plaatsen;

    public long getId() {
        return id;
    }

    public String getPlaatsen() {
        return plaatsen;
    }
}
