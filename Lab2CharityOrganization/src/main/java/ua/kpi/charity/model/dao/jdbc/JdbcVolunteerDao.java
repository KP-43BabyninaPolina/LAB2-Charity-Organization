package ua.kpi.charity.model.dao.jdbc;

import ua.kpi.charity.model.dao.VolunteerDao;
import ua.kpi.charity.model.entities.Event;
import ua.kpi.charity.model.entities.Volunteer;
import ua.kpi.charity.model.dao.exceptions.DaoException;

import java.sql.*;
import java.util.Optional;

public class JdbcVolunteerDao extends AbstractJdbcDao<Volunteer> implements VolunteerDao {
    private static final String DELETE_VOLUNTEER_BY_ID = "DELETE FROM volunteer WHERE id = ? ";
    private static final String INSERT_INTO_VOLUNTEER = "INSERT INTO volunteer " +
            "(first_name, last_name, birth_date, phone_number, event_id)" +
            " VALUES ( ?, ?, ?, ?, ? ) ";

    private static final String SELECT_FROM_VOLUNTEER = "SELECT * FROM volunteer ";
    private static final String WHERE_ID = "WHERE id = ? ";
    private static final String WHERE_PHONE = "WHERE phone_number = ? ";

    private static final String UPDATE_VOLUNTEER = "UPDATE volunteer " +
            "SET first_name = ?, last_name = ?, birth_date = ?, phone_number = ?, event_id = ? " ;

    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String BIRTH_DATE = "birth_date";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String EVENT_ID = "event_id";

    public JdbcVolunteerDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return SELECT_FROM_VOLUNTEER;
    }

    @Override
    protected String getCreateQuery() {
        return INSERT_INTO_VOLUNTEER;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_VOLUNTEER + WHERE_ID;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_VOLUNTEER_BY_ID;
    }

    @Override
    protected Volunteer getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return getVolunteerFromResultSet(resultSet);
    }

    static Volunteer getVolunteerFromResultSet(ResultSet resultSet) throws SQLException {
        return new Volunteer.Builder()
                .setId(resultSet.getInt(ID))
                .setFirstName(resultSet.getString(FIRST_NAME))
                .setLastName(resultSet.getString(LAST_NAME))
                .setBirthDate(resultSet.getDate(BIRTH_DATE).toLocalDate())
                .setPhoneNumber(resultSet.getString(PHONE_NUMBER))
                .setEvent(new Event.Builder().setId(resultSet.getInt(EVENT_ID)).build())
                .build();
    }

    @Override
    protected void setIdForEntity(Volunteer entity, int id) {
        entity.setId(id);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement query, Volunteer entity)
            throws SQLException {
        query.setString(1, entity.getFirstName());
        query.setString(2, entity.getLastName());
        query.setDate(3, Date.valueOf(entity.getBirthDate()));
        query.setString(4, entity.getPhoneNumber());
        query.setInt(5, entity.getEvent().getId());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement query, Volunteer entity) throws SQLException {
        query.setString(1, entity.getFirstName());
        query.setString(2, entity.getLastName());
        query.setDate(3, Date.valueOf(entity.getBirthDate()));
        query.setString(4, entity.getPhoneNumber());
        query.setInt(5, entity.getEvent().getId());
        query.setInt(6, entity.getId());
    }

    @Override
    protected String getSelectByIdQuery() {
        return SELECT_FROM_VOLUNTEER + WHERE_ID;
    }

    @Override
    public Optional<Volunteer> findByPhoneNumber(String phoneNumber) {
        Optional<Volunteer> result = Optional.empty();

        try(PreparedStatement query =
                    connection.prepareStatement(SELECT_FROM_VOLUNTEER + WHERE_PHONE) ){
            query.setString(1, phoneNumber);
            ResultSet rs = query.executeQuery();
            if(rs.next()) {
                Volunteer volunteer = getEntityFromResultSet(rs);
                result = Optional.of(volunteer);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return result;
    }
}