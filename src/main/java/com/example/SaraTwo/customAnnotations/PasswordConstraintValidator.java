package com.example.SaraTwo.customAnnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        boolean isValid = true;
        String numbers = "(.*[0-9].*)";
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String specialChars = "(.*[@,#,$,%].*$)";

        if (password.length() < 8) {
            System.out.println("Password must be greater than 8");
            isValid = false;
        }
        if (password.length() > 15) {
            System.out.println("Password must be less than 15");
            isValid = false;
        }
        if (!password.matches(upperCaseChars)) {
            System.out.println("Password must have at least one Uppercase");
            isValid = false;
        }
        if (!password.matches(lowerCaseChars)) {
            System.out.println("Password must have at least one lowercase");
            isValid = false;
        }
        if (!password.matches(numbers)) {
            System.out.println("Password must have at least one number");
            isValid = false;
        }
        if (!password.matches(specialChars)) {
            System.out.println("Password must have at least one special character");
            isValid = false;
        }


        return isValid;
    }
}
