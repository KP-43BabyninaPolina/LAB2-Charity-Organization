package ua.kpi.charity.controller.validators;

import ua.kpi.charity.model.entities.Volunteer;
import ua.kpi.charity.utils.AttributesHolder;
import ua.kpi.charity.utils.ErrorMessages;

import java.util.regex.Pattern;

public class VolunteerValidator implements Validator<Volunteer> {
    private final Pattern namePattern;
    private final PhoneNumberValidator phoneNumberValidator;
    private final BirthDateValidator birthDateValidator;
    private final EventValidator eventValidator;

    public VolunteerValidator() {
        namePattern = Pattern.compile(RegExp.NAME);
        phoneNumberValidator = new PhoneNumberValidator();
        birthDateValidator = new BirthDateValidator();
        eventValidator = new EventValidator();
    }

    @Override
    public boolean validate(Volunteer volunteer, Errors errors) {
        if (volunteer != null) {

            if (volunteer.getFirstName() == null || !namePattern.matcher(volunteer.getFirstName()).matches()) {
                reject(errors, AttributesHolder.FIRST_NAME, ErrorMessages.FIRST_NAME_INVALID);
            }

            if (volunteer.getLastName() == null || !namePattern.matcher(volunteer.getLastName()).matches()) {
                reject(errors, AttributesHolder.LAST_NAME, ErrorMessages.LAST_NAME_INVALID);
            }

            phoneNumberValidator.validate(volunteer.getPhoneNumber(), errors);
            birthDateValidator.validate(volunteer.getBirthDate(), errors);
            eventValidator.validate(volunteer.getEvent(), errors);

        } else {
            reject(errors, AttributesHolder.VOLUNTEER, ErrorMessages.INVALID);
        }
        return !errors.hasError();
    }

    @Override
    public boolean validate(Volunteer volunteer) {
        if (volunteer == null) {
            return false;
        }

        return namePattern.matcher(volunteer.getFirstName()).matches() &&
                namePattern.matcher(volunteer.getLastName()).matches() &&
                phoneNumberValidator.validate(volunteer.getPhoneNumber()) &&
                birthDateValidator.validate(volunteer.getBirthDate()) &&
                eventValidator.validate(volunteer.getEvent());
    }

    private void reject(Errors errors, String attribute, String message){
        errors.addMessage(attribute, message);
        errors.setResult(false);
    }
}