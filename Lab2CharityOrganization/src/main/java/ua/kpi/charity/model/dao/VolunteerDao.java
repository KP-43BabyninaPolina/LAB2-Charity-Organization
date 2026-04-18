package ua.kpi.charity.model.dao;

import ua.kpi.charity.model.entities.Volunteer;

import java.util.List;
import java.util.Optional;

public interface VolunteerDao extends GenericDao<Volunteer> {
    List<Volunteer> findByLastName(String lastName);
    Optional<Volunteer> findByPhoneNumber(String phoneNumber);
}
