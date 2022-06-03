package com.example.SaraTwo.customAnnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<Password,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        boolean firstLetter = s.startsWith("Z");
        boolean lastLetter = s.endsWith("o");
        boolean contains = s.contains("A");

        if (firstLetter)
            if (lastLetter)
                if (contains)
                    return  true;
        return false;
    }
}
