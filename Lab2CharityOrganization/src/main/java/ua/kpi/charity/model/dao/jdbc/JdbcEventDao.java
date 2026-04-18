package ua.kpi.charity.model.dao.jdbc;

import ua.kpi.charity.model.dao.EventDao;
import ua.kpi.charity.model.entities.Event;
import ua.kpi.charity.model.dao.exceptions.DaoException;
import ua.kpi.charity.model.entities.EventStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcEventDao extends AbstractJdbcDao<Event> implements EventDao {
    private static final String DELETE_EVENT_BY_ID =
            "DELETE FROM event WHERE id = ? ";
    private static final String INSERT_INTO_EVENT =
            "INSERT INTO event (name, status) VALUES ( ?, ? ) ";
    private static final String SELECT_FROM_EVENT =
            "SELECT * FROM event ";
    private static final String ORDER_BY_ID =
            "ORDER BY event.id ";
    private static final String WHERE_ID =
            "WHERE id = ? ";
    private static final String WHERE_STATUS =
            "WHERE status = ? ";

    private static final String UPDATE_EVENT =
            "UPDATE event SET name = ?, status = ? WHERE id = ? ";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String STATUS = "status";

    public JdbcEventDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return SELECT_FROM_EVENT + ORDER_BY_ID;
    }

    @Override
    protected String getCreateQuery() {
        return INSERT_INTO_EVENT;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_EVENT;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_EVENT_BY_ID;
    }

    @Override
    protected Event getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return getEventFromResultSet(resultSet);
    }

    static Event getEventFromResultSet(ResultSet resultSet) throws SQLException {
        return new Event.Builder()
                .setId(resultSet.getInt(ID))
                .setName(resultSet.getString(NAME))
                .setStatus(EventStatus.valueOf(resultSet.getString(STATUS)))
                .build();
    }

    @Override
    protected void setIdForEntity(Event entity, int id) {
        entity.setId(id);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement query, Event entity)
            throws SQLException {
        query.setString(1, entity.getName());
        query.setString(2, entity.getStatus().name());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement query, Event entity)
            throws SQLException {
        query.setString(1, entity.getName());
        query.setString(2, entity.getStatus().name());
        query.setInt(3, entity.getId());
    }

    @Override
    protected String getSelectByIdQuery() {
        return SELECT_FROM_EVENT + WHERE_ID;
    }

    @Override
    public List<Event> findByName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Event> findByStatus(EventStatus status) {
        List<Event> events = new ArrayList<>();

        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_EVENT + WHERE_STATUS)) {
            query.setString(1, status.name());
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                events.add(getEntityFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return events;
    }
}