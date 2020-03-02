package be.vdab.cultuurhuis.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Target;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = GebruikersnaamMoetNieuweZijnValidator.class)
public @interface GebruikersnaamMoetNieuweZijn {
    String message() default "{be.vdab.cultuurhuis.constraints.GebruikersnaamMoetNieuweZijn.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
