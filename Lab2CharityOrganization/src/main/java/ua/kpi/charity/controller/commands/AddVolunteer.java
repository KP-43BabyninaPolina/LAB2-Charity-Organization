package ua.kpi.charity.controller.commands;

import ua.kpi.charity.controller.FrontController;
import ua.kpi.charity.controller.validators.Errors;
import ua.kpi.charity.controller.validators.VolunteerValidator;
import ua.kpi.charity.controller.validators.Validator;
import ua.kpi.charity.model.entities.Event;
import ua.kpi.charity.model.entities.Volunteer;
import ua.kpi.charity.model.services.EventService;
import ua.kpi.charity.model.services.VolunteerService;
import ua.kpi.charity.utils.AttributesHolder;
import ua.kpi.charity.utils.PagesHolder;
import ua.kpi.charity.utils.PathsHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class AddVolunteer implements Command {
    private final EventService eventService = EventService.getInstance();
    private final VolunteerService volunteerService = VolunteerService.getInstance();
    private final Validator<Volunteer> volunteerValidator = new VolunteerValidator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Volunteer volunteer = buildVolunteer(request);
        Errors errors = new Errors();

        if (volunteerValidator.validate(volunteer, errors)) {
            volunteerService.create(volunteer);
            response.sendRedirect(PathsHolder.VOLUNTEERS);
            return FrontController.REDIRECT;
        }

        request.removeAttribute(AttributesHolder.ERROR_MESSAGE);
        request.setAttribute(AttributesHolder.ERRORS, errors);
        request.setAttribute(AttributesHolder.NEW_MODE, true);

        List<Event> availableEvents = eventService.getEventsForVolunteer();
        request.setAttribute(AttributesHolder.EVENTS, availableEvents);
        request.setAttribute(AttributesHolder.VOLUNTEER, volunteer);

        return PagesHolder.VOLUNTEER;
    }

    private Volunteer buildVolunteer(HttpServletRequest request) {
        String birthDateStr = request.getParameter(AttributesHolder.BIRTH_DATE);
        LocalDate birthDate = null;
        if (birthDateStr != null && !birthDateStr.trim().isEmpty()) {
            try { birthDate = LocalDate.parse(birthDateStr); } catch (DateTimeParseException ignored) {}
        }

        Event event = null;
        String eventIdParam = request.getParameter(AttributesHolder.EVENT);
        if (eventIdParam != null && !eventIdParam.trim().isEmpty()) {
            try {
                event = new Event.Builder().setId(Integer.parseInt(eventIdParam)).build();
            } catch (NumberFormatException ignored) {}
        }

        return new Volunteer.Builder()
                .setFirstName(request.getParameter(AttributesHolder.FIRST_NAME))
                .setLastName(request.getParameter(AttributesHolder.LAST_NAME))
                .setPhoneNumber(request.getParameter(AttributesHolder.PHONE_NUMBER))
                .setBirthDate(birthDate)
                .setEvent(event)
                .build();
    }
}