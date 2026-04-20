package ua.kpi.charity.controller.validators;

import ua.kpi.charity.utils.AttributesHolder;
import ua.kpi.charity.utils.ErrorMessages;

import java.time.LocalDate;
import java.time.Period;

public class BirthDateValidator implements Validator<LocalDate> {

    @Override
    public boolean validate(LocalDate birthDate, Errors errors) {
        if (!validate(birthDate)) {
            errors.setResult(false);
            errors.addMessage(AttributesHolder.BIRTH_DATE, ErrorMessages.BIRTH_DATE_INVALID);
            return errors.getResult();
        }
        return true;
    }

    @Override
    public boolean validate(LocalDate birthDate) {
        if (birthDate == null) {
            return false;
        }

        int age = Period.between(birthDate, LocalDate.now()).getYears();

        return age >= 18 && age <= 120;
    }
}