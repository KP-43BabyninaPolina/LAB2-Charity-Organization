package ua.kpi.charity.exceptions;

import org.apache.log4j.Logger;

public class ApplicationException extends RuntimeException {
    private static final Logger logger = Logger.getLogger(ApplicationException.class);
    private String messageKey;
    private String logMessage;

    protected ApplicationException() { }

    protected ApplicationException(String messageKey) {
        this.messageKey = messageKey;
    }

    protected ApplicationException(String messageKey, Throwable cause) {
        super(cause);
        logger.error(cause.getMessage());
        this.messageKey = messageKey;
    }

    protected ApplicationException addLogMessage(String logMessage) {
        this.logMessage = logMessage;
        return this;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getLogMessage() {
        return logMessage;
    }
}
