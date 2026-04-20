package ua.kpi.charity.utils;

public class ErrorMessages {
    public static final String PHONE_NUMBER_INVALID = "Некоректний номер телефону";
    public static final String FIRST_NAME_INVALID = "Некоректне ім'я";
    public static final String LAST_NAME_INVALID = "Некоректне прізвище";
    public static final String BIRTH_DATE_INVALID = "Некоректна дата народження (реєстрація від 18-ти років)";
    public static final String NO_EVENT_SELECTED = "Оберіть захід для реєстрації на участь:";
    public static final String EVENT_NOT_AVAILABLE_FOR_REGISTRATION = "Відхилено: реєстрація буде відкрита після закриття збору на фінансування. Дивіться сайт організації для детальнішої інформації.";
    public static final String INVALID = "Некоректна реєстраційна форма волонтера";

    public static final String DAO_ERROR = "Помилка у роботі DAO";
    public static final String SERVICE_ERROR = "Помилка у роботі сервісу";
    public static final String SERVICE_ERROR_VOLUNTEER_EXIST = "Волонтер з таким номером телефону вже існує.";
    public static final String NOT_EXCEPTED_ERROR = "Неочікувана помилка. Спробуйте знову пізніше";
}
