package ru.android_studio.gibdd_servis.driver.model.common;

public class Found {
    private int code = 100;
    private Doc doc;
    private String message = "Ответ сервера успешно получен";
    private String[] decis;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getDecis() {
        return decis;
    }

    public void setDecis(String[] decis) {
        this.decis = decis;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }
}
