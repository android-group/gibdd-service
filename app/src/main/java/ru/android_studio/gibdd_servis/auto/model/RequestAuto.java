package ru.android_studio.gibdd_servis.auto.model;

import ru.android_studio.gibdd_servis.auto.gibdd.CheckAutoType;

/**
 * Created by y.andreev on 03.07.2016.
 */
public class RequestAuto {

    private String vin;
    private String captchaWord;
    private CheckAutoType checkAutoType;
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

    public CheckAutoType getCheckAutoType() {
        return checkAutoType;
    }

    public void setCheckAutoType(CheckAutoType checkAutoType) {
        this.checkAutoType = checkAutoType;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }
}
