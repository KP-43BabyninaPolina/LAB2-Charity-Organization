package ua.kpi.charity.model.dao;

import ua.kpi.charity.model.entities.Event;
import ua.kpi.charity.model.entities.EventStatus;

import java.util.List;

public interface EventDao extends GenericDao<Event> {
    List<Event> findByName (String name);
    List<Event> findByStatus(EventStatus status);
}
