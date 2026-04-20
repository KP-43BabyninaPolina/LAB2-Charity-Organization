package ua.kpi.charity.controller.commands;

import ua.kpi.charity.model.entities.Volunteer;
import ua.kpi.charity.model.services.VolunteerService;
import ua.kpi.charity.utils.AttributesHolder;
import ua.kpi.charity.utils.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetVolunteers implements Command {
    private final VolunteerService volunteerService = VolunteerService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Volunteer> volunteers = volunteerService.getAll();

        request.setAttribute(AttributesHolder.VOLUNTEERS, volunteers);

        return PagesHolder.VOLUNTEERS;
    }
}