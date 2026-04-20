package ua.kpi.charity.controller.validators;

import ua.kpi.charity.model.entities.Event;
import ua.kpi.charity.model.entities.EventStatus;
import ua.kpi.charity.model.services.EventService;
import ua.kpi.charity.utils.AttributesHolder;
import ua.kpi.charity.utils.ErrorMessages;

import java.util.Optional;

public class EventValidator implements Validator<Event> {
    private final EventService eventService = EventService.getInstance();

    @Override
    public boolean validate(Event event, Errors errors) {
        if (event == null) {
            errors.setResult(false);
            errors.addMessage(AttributesHolder.EVENT, ErrorMessages.NO_EVENT_SELECTED);
            return false;
        }

        Optional<Event> eventOptional = eventService.getById(event.getId());

        if (eventOptional.isPresent()) {
            Event dbEvent = eventOptional.get();
            if (dbEvent.getStatus() != EventStatus.SUM_GOAL_REACHED) {
                errors.setResult(false);
                errors.addMessage(AttributesHolder.EVENT, ErrorMessages.EVENT_NOT_AVAILABLE_FOR_REGISTRATION);
                return false;
            }
        } else {
            errors.setResult(false);
            errors.addMessage(AttributesHolder.EVENT, "Event not found");
            return false;
        }

        return true;
    }

    @Override
    public boolean validate(Event event) {
        if (event == null) return false;

        Optional<Event> eventOptional = eventService.getById(event.getId());
        return eventOptional.isPresent() && eventOptional.get().getStatus() == EventStatus.SUM_GOAL_REACHED;
    }
}