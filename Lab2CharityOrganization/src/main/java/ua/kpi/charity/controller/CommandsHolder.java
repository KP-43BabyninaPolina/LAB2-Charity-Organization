package ua.kpi.charity.controller;

import ua.kpi.charity.controller.commands.*;
import ua.kpi.charity.utils.PagesHolder;
import ua.kpi.charity.utils.PathsHolder;

import java.util.HashMap;
import java.util.Map;

public class CommandsHolder  {
    public static final String DELIMITER = ":";
    private static final String GET = "GET" + DELIMITER;
    private static final String POST = "POST" + DELIMITER;

    private final Map<String , Command> commands = new HashMap<>();

    public CommandsHolder() {
        initCommands();
    }

    private void initCommands() {
        commands.put(GET + PathsHolder.VOLUNTEERS, new GetVolunteers());
        commands.put(GET + PathsHolder.ADD_VOLUNTEER, new GetVolunteer());
        commands.put(GET + PathsHolder.EDIT_VOLUNTEER, new GetVolunteer());

        commands.put(POST + PathsHolder.ADD_VOLUNTEER, new AddVolunteer());
        commands.put(POST + PathsHolder.EDIT_VOLUNTEER, new EditVolunteer());
        commands.put(POST + PathsHolder.DELETE_VOLUNTEER, new DeleteVolunteer());
    }

    Command getCommand(String commandKey) {
        return commands.getOrDefault(commandKey, (req , resp)-> PagesHolder.PAGE_NOT_FOUND);
    }
}
