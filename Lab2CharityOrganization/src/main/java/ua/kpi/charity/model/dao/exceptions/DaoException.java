package ua.kpi.charity.model.dao.exceptions;

import ua.kpi.charity.exceptions.ApplicationException;
import ua.kpi.charity.utils.ErrorMessages;

public class DaoException extends ApplicationException {
    public DaoException(){
        super(ErrorMessages.DAO_ERROR);
    }

    public DaoException(Exception cause) {
        super(ErrorMessages.DAO_ERROR, cause);
    }
}
