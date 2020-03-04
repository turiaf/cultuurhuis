package be.vdab.cultuurhuis.domain;

import be.vdab.cultuurhuis.constraints.NaamZonderHtml;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
@Access(AccessType.FIELD)
public class Adres {
    @NotBlank
    @NaamZonderHtml
    private String straat;
    @NotBlank
    @NaamZonderHtml
    private String huisnr;
    @NotBlank
    @NaamZonderHtml
    @Length(min = 1, max = 4)
    private String postcode;
    @NotBlank
    @NaamZonderHtml
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
