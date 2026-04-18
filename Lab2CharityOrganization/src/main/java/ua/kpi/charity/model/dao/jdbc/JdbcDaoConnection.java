package ua.kpi.charity.model.dao.jdbc;
import ua.kpi.charity.model.dao.DaoConnection;
import ua.kpi.charity.model.dao.exceptions.DaoException;

import java.sql.*;

public class JdbcDaoConnection implements DaoConnection {
    private final Connection connection;
    private boolean inTransaction = false;

    public JdbcDaoConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void begin() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            inTransaction = false;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            inTransaction = false;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() {
        if(inTransaction) {
            rollback();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    Connection getConnection() {
        return connection;
    }
}
