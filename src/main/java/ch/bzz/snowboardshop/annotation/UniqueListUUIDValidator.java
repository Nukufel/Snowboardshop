package ch.bzz.snowboardshop.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;


public class UniqueListUUIDValidator implements ConstraintValidator<UniqueListUUID, List<String>> {

    @Override
    public boolean isValid(List<String> strings, ConstraintValidatorContext constraintValidatorContext) {
        if(strings == null){
            return true;
        }

        HashSet<String> hashSet = new HashSet<>(strings);

        Boolean ret = false;

        if(strings.size() == hashSet.size()){
            ret = true;
        }

        return ret;
    }
}
