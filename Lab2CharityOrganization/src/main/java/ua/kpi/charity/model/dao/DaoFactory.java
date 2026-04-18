package ua.kpi.charity.model.dao;

import ua.kpi.charity.model.dao.exceptions.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public abstract class DaoFactory {
    public abstract DaoConnection getConnection();

    public abstract EventDao createEventDao(DaoConnection daoConnection);
    public abstract VolunteerDao createVolunteerDao(DaoConnection daoConnection);

    public static final String DB_FILE = "/db.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";
    private static  DaoFactory instance;

    public static DaoFactory getInstance(){
        if( instance == null) {
            try {
                InputStream inputStream =
                        DaoFactory.class.getResourceAsStream(DB_FILE);
                Properties dbProps = new Properties();
                dbProps.load(inputStream);
                String factoryClass = dbProps.getProperty(DB_FACTORY_CLASS);
                instance = (DaoFactory) Class.forName(factoryClass).getDeclaredConstructor().newInstance();

            } catch (IOException | IllegalAccessException |
                     InstantiationException | ClassNotFoundException e ) {
                throw new DaoException(e);
            } catch (InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }
}
