package ua.kpi.charity.controller.commands;

import org.apache.log4j.Logger;
import ua.kpi.charity.controller.FrontController;
import ua.kpi.charity.model.services.VolunteerService;
import ua.kpi.charity.utils.PathsHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteVolunteer implements Command {
    private final VolunteerService volunteerService = VolunteerService.getInstance();

    private static final Logger logger = Logger.getLogger(DeleteVolunteer.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getRequestURI();

        try {
            int volunteerId = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
            logger.info("Deleting volunteer " + volunteerId);
            volunteerService.delete(volunteerId);
        }
        catch (NumberFormatException e) {
            logger.error("Invalid volunteer ID format for deletion: " + path);
        }

        response.sendRedirect(PathsHolder.VOLUNTEERS);

        return FrontController.REDIRECT;
    }
}