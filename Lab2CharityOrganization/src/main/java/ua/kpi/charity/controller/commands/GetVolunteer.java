package ua.kpi.charity.controller.commands;

import ua.kpi.charity.model.entities.Event;
import ua.kpi.charity.model.entities.Volunteer;
import ua.kpi.charity.model.services.EventService;
import ua.kpi.charity.model.services.VolunteerService;
import ua.kpi.charity.utils.AttributesHolder;
import ua.kpi.charity.utils.PagesHolder;
import ua.kpi.charity.utils.PathsHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class GetVolunteer implements Command {
    private final EventService eventService = EventService.getInstance();
    private final VolunteerService volunteerService = VolunteerService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Event> eventList = eventService.getEventsForVolunteer();
        request.setAttribute(AttributesHolder.EVENTS, eventList);

        String path = request.getRequestURI();
        if (path.contains(PathsHolder.EDIT_VOLUNTEER)) {
            try {
                int volunteerId = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
                Optional<Volunteer> volunteer = volunteerService.getById(volunteerId);
                volunteer.ifPresent(v -> request.setAttribute(AttributesHolder.VOLUNTEER, v));
            } catch (NumberFormatException e) {
            }
        }

        return PagesHolder.VOLUNTEER;
    }
}