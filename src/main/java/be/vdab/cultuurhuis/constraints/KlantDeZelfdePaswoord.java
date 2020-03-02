package be.vdab.cultuurhuis.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

import java.lang.annotation.*;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = NieuweKlantFormPaswoordGelijkAanHerhaalPaswoordValidator.class)
public @interface KlantDeZelfdePaswoord {
    String message() default "{be.vdab.cultuurhuis.constraints.PaswoordGelijkAanHerhaalPaswoord.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
