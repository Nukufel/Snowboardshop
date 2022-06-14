package ch.bzz.snowboardshop.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueListUUIDValidator.class)
@Target({ElementType.PARAMETER})
@Documented
public @interface UniqueListUUID {

    String message() default "multiple Snowboard are the same: {value}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload () default {};
    @interface List{
        UniqueListUUID[] value();
    }

}
