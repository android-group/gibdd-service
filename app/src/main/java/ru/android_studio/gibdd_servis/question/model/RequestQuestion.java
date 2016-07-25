package ru.android_studio.gibdd_servis.question.model;

/**
 * Created by y.andreev on 03.07.2016.
 */
public class RequestQuestion {

    private String num;
    private String captchaWord;
    private String date;
    private String jsessionid;

    public String getNum() {
        return num;
    }

    public void setNum(String vin) {
        this.num = vin;
    }

    public String getCaptchaWord() {
        return captchaWord;
    }

    public void setCaptchaWord(String captchaWord) {
        this.captchaWord = captchaWord;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }
}
