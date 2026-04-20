package ua.kpi.charity.model.dao;

import ua.kpi.charity.model.entities.Event;
import ua.kpi.charity.model.entities.EventStatus;

import java.util.List;
import java.util.Optional;

public interface EventDao extends GenericDao<Event> {
    Optional<Event> findById (int id);
    List<Event> findByStatus(EventStatus status);
}
