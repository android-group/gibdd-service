package ru.android_studio.gibdd_servis.driver.model.common;

public class NotFound {
    // "code"
    private int code = 200;

    // "doc"
    private String doc = null;

    // "message"
    private String message = "Ответ от серера получен. Записей не найдено";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
