package ru.android_studio.gibdd_servis.fine.model;

/**
 * Created by y.andreev on 03.07.2016.
 */
public class RequestFine {

    private String req; //fines:111111111:222:2122222222
    private String captchaWord;
    private String regnum;
    private String regreg;
    private String stsnum;
    private String jsessionid;

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public String getCaptchaWord() {
        return captchaWord;
    }

    public void setCaptchaWord(String captchaWord) {
        this.captchaWord = captchaWord;
    }

    public String getRegnum() {
        return regnum;
    }

    public void setRegnum(String regnum) {
        this.regnum = regnum;
    }

    public String getRegreg() {
        return regreg;
    }

    public void setRegreg(String regreg) {
        this.regreg = regreg;
    }

    public String getStsnum() {
        return stsnum;
    }

    public void setStsnum(String stsnum) {
        this.stsnum = stsnum;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }
}
