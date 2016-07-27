package ru.android_studio.gibdd_servis.fine.model;

/**
 * Created by y.andreev on 03.07.2016.
 */
public class RequestFine {

    /**
     * Капча
     */
    private String captchaWord;
    /**
     * Государственный регистрационный знак
     */
    private String regnum;

    /**
     * Государственный регистрационный знак
     * Регион
     */
    private String regreg;

    /**
     * Серия и номер свидетельства о регистрации
     */
    private String stsnum;

    private String phpSessId;

    public String getReq() {
        return String.format("fines:%s:%s:%s", regnum, regreg, stsnum);
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

    public String getPhpSessId() {
        return phpSessId;
    }

    public void setPhpSessId(String phpSessId) {
        this.phpSessId = phpSessId;
    }
}
