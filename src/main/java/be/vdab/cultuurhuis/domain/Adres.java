package be.vdab.cultuurhuis.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Access(AccessType.FIELD)
public class Adres {
    @NotNull
    private String straat;
    @NotNull
    private String huisnr;
    @NotNull
    private String postcode;
    @NotNull
    private String gemeente;

    protected Adres() {
    }

    public Adres(String straat, String huisnr, String postcode, String gemeente) {
        this.straat = straat;
        this.huisnr = huisnr;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisnr() {
        return huisnr;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }
}
