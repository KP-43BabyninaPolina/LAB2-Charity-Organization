package ua.kpi.charity.model.dao;

import ua.kpi.charity.model.entities.Volunteer;

import java.util.Optional;

public interface VolunteerDao extends GenericDao<Volunteer> {
    Optional<Volunteer> findByPhoneNumber(String phoneNumber);
}
