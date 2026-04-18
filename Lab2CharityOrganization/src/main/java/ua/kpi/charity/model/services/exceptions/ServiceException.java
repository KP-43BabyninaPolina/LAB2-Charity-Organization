package ua.kpi.charity.model.services.exceptions;

import ua.kpi.charity.exceptions.ApplicationException;
import ua.kpi.charity.utils.ErrorMessages;

public class ServiceException extends ApplicationException {
    public ServiceException(){
        super(ErrorMessages.SERVICE_ERROR);
    }

    public ServiceException(Exception cause) {
        super(ErrorMessages.SERVICE_ERROR, cause);
    }

    public ServiceException(String messageKey) {
        super(messageKey);
    }

    @Override
    public ServiceException addLogMessage(String logMessage) {
        super.addLogMessage(logMessage);
        return this;
    }
}