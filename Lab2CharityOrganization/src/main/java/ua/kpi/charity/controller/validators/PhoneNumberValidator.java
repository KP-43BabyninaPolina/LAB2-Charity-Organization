package ua.kpi.charity.controller.validators;

import ua.kpi.charity.utils.AttributesHolder;
import ua.kpi.charity.utils.ErrorMessages;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements Validator<String> {
    private final Pattern phonePattern;

    public PhoneNumberValidator() {
        phonePattern = Pattern.compile(RegExp.PHONE);
    }

    @Override
    public boolean validate(String phoneNumber, Errors errors) {
        if (!validate(phoneNumber)) {
            errors.setResult(false);
            errors.addMessage(AttributesHolder.PHONE_NUMBER, ErrorMessages.PHONE_NUMBER_INVALID);
            return errors.getResult();
        }
        return true;
    }

    @Override
    public boolean validate(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return phonePattern.matcher(phoneNumber).matches();
    }
}