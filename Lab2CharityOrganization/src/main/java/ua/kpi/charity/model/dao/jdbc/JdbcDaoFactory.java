package ua.kpi.charity.model.dao.jdbc;

import ua.kpi.charity.model.dao.*;
import ua.kpi.charity.model.dao.exceptions.DaoException;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {
    private final DataSource dataSource;

    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/charity_db");
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public EventDao createEventDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) daoConnection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcEventDao(sqlConnection);
    }

    @Override
    public VolunteerDao createVolunteerDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) daoConnection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcVolunteerDao(sqlConnection);
    }
}