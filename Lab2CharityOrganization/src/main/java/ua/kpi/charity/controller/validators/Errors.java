package ua.kpi.charity.controller.validators;

import java.util.HashMap;
import java.util.Map;

public class Errors {
    private final Map<String, String> messages = new HashMap<>();
    private boolean result = true;

    public void addMessage(String attribute, String message) {
        messages.put(attribute, message);
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public boolean hasError(){
        return !result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }
}