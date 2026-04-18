package ua.kpi.charity.model.dao.jdbc;

import ua.kpi.charity.model.dao.GenericDao;
import ua.kpi.charity.model.dao.exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractJdbcDao<E> implements GenericDao<E> {
    protected Connection connection;

    public AbstractJdbcDao(Connection connection) {
        this.connection = connection;
    }

    protected abstract String getSelectAllQuery();

    protected abstract String getCreateQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract E getEntityFromResultSet(ResultSet resultSet) throws SQLException;

    protected abstract void setIdForEntity(E entity, int id);

    protected abstract void prepareStatementForInsert(PreparedStatement query, E entity) throws SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement query, E entity) throws SQLException;

    @Override
    public Optional<E> find(Integer id) {
        Optional<E> result = Optional.empty();
        try(PreparedStatement query =
                    connection.prepareStatement(getSelectByIdQuery())){
            query.setInt( 1 , id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    protected abstract String getSelectByIdQuery();

    @Override
    public List<E> findAll() {
        List<E> result = new ArrayList<>();
        try(Statement query =
                    connection.createStatement();
            ResultSet resultSet = query.executeQuery(getSelectAllQuery())){
            while (resultSet.next()) {
                result.add( getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public void create(E entity) {
        try( PreparedStatement query =
                     connection.prepareStatement(getCreateQuery(),
                             Statement.RETURN_GENERATED_KEYS ) ){
            prepareStatementForInsert(query, entity);
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                setIdForEntity(entity, keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(E entity) {
        try (PreparedStatement query =
                     connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(query, entity);
            int count = query.executeUpdate();
            if (count != 1) {
                throw new DaoException();
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement query =
                     connection.prepareStatement(getDeleteQuery())) {
            query.setInt(1, id);
            query.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
