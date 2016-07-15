package ru.android_studio.olga.gibdd_servis.car.model;

import ru.android_studio.olga.gibdd_servis.gibdd.Request;

/**
 * Created by y.andreev on 03.07.2016.
 */
public class CarRequest implements Request {
    private String vin;
    private String captcha;
    private String sessionId;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
