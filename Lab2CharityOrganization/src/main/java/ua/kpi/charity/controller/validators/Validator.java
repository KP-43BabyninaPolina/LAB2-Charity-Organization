package ua.kpi.charity.controller.validators;

public interface Validator<E> {
    boolean validate(E t, Errors errors);
    boolean validate(E t);
}
