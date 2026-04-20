package ua.kpi.charity.model.services;

import ua.kpi.charity.model.dao.DaoConnection;
import ua.kpi.charity.model.dao.DaoFactory;
import ua.kpi.charity.model.dao.EventDao;
import ua.kpi.charity.model.entities.Event;
import ua.kpi.charity.model.entities.EventStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final EventService INSTANCE = new EventService();
    }

    public static EventService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Event> getAll() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            EventDao eventDao = daoFactory.createEventDao(connection);
            return eventDao.findAll();
        }
    }

    public Optional<Event> getById(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            EventDao eventDao = daoFactory.createEventDao(connection);
            return eventDao.findById(id);
        }
    }

    public List<Event> getEventsForVolunteer() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            EventDao eventDao = daoFactory.createEventDao(connection);

            List<Event> availableEvents = eventDao.findByStatus(EventStatus.SUM_GOAL_REACHED);
            List<Event> plannedEvents = eventDao.findByStatus(EventStatus.PLANNED);

            List<Event> combinedList = new ArrayList<>();

            combinedList.addAll(availableEvents);
            combinedList.addAll(plannedEvents);

            return combinedList;
        }
    }
}