package ru.android_studio.gibdd_servis.auto.model;

import ru.android_studio.gibdd_servis.auto.model.history.ResponseStatus;

/**
 * Created by yuryandreev on 25/09/16.
 */
public class ResultAuto {

    protected String response;
    protected ResponseStatus type;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ResponseStatus getType() {
        return type;
    }

    public void setType(ResponseStatus type) {
        this.type = type;
    }
}
