package be.vdab.cultuurhuis.constraints;

import org.hibernate.validator.constraints.Range;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "\\w+")
public @interface NaamZonderHtml {
    @OverridesAttribute(constraint = Pattern.class, name = "message")
    String message() default "{be.vdab.cultuurhuis.constraints.NaamZonderHtml.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
