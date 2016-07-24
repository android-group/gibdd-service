package ru.android_studio.gibdd_servis.car.model;

import ru.android_studio.gibdd_servis.car.gibdd.CheckType;

/**
 * Created by y.andreev on 03.07.2016.
 */
public class CarRequest {

    private String vin;
    private String captchaWord;
    private CheckType checkType;
    private String jsessionid;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCaptchaWord() {
        return captchaWord;
    }

    public void setCaptchaWord(String captchaWord) {
        this.captchaWord = captchaWord;
    }

    public CheckType getCheckType() {
        return checkType;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }
}
