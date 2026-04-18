package ua.kpi.charity.model.services;

import ua.kpi.charity.model.dao.DaoConnection;
import ua.kpi.charity.model.dao.DaoFactory;
import ua.kpi.charity.model.dao.VolunteerDao;
import ua.kpi.charity.model.entities.Volunteer;
import ua.kpi.charity.model.services.exceptions.ServiceException;
import ua.kpi.charity.utils.ErrorMessages;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class VolunteerService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public static final String SERVICE_ERROR_VOLUNTEER_EXIST =
            "Volunteer with this phone number ('%s') already exists";

    private static class Holder{
        static final VolunteerService INSTANCE = new VolunteerService();
    }

    public static VolunteerService getInstance(){
        return Holder.INSTANCE;
    }

    public void create(Volunteer volunteer) {
        try( DaoConnection connection = daoFactory.getConnection() ){
            VolunteerDao dao = daoFactory.createVolunteerDao(connection);
            connection.begin();
            Optional<Volunteer> existingVolunteer = dao.findByPhoneNumber(volunteer.getPhoneNumber());
            checkIfVolunteerAlreadyExist(existingVolunteer);
            dao.create(volunteer);
            connection.commit();
        }
    }

    public void update(Volunteer volunteer) {
        try( DaoConnection connection = daoFactory.getConnection() ){
            VolunteerDao dao = daoFactory.createVolunteerDao(connection);
            connection.begin();
            Optional<Volunteer> existingVolunteer = dao.findByPhoneNumber(volunteer.getPhoneNumber());
            existingVolunteer.ifPresent(existing -> {
                if (!Objects.equals(volunteer.getId(), existing.getId())) {
                    checkIfVolunteerAlreadyExist(existingVolunteer);
                }
            });
            dao.update(volunteer);
            connection.commit();
        }
    }

    public void delete(int volunteerId) {
        try( DaoConnection connection = daoFactory.getConnection() ){
            VolunteerDao dao = daoFactory.createVolunteerDao(connection);
            dao.delete(volunteerId);
        }
    }

    public Optional<Volunteer> getById(Integer id) {
        try( DaoConnection connection = daoFactory.getConnection() ){
            VolunteerDao dao = daoFactory.createVolunteerDao(connection);
            return dao.find(id);
        }
    }

    public List<Volunteer> getAll() {
        try( DaoConnection connection = daoFactory.getConnection() ){
            VolunteerDao dao = daoFactory.createVolunteerDao(connection);
            return dao.findAll();
        }
    }

    private void checkIfVolunteerAlreadyExist(Optional<Volunteer> existingVolunteer) {
        existingVolunteer.ifPresent(volunteer -> {
            throw new ServiceException(
                    ErrorMessages.SERVICE_ERROR_VOLUNTEER_EXIST)
                    .addLogMessage(String
                            .format(SERVICE_ERROR_VOLUNTEER_EXIST, volunteer.getPhoneNumber()));
        });
    }
}