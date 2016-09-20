package ru.android_studio.gibdd_servis.auto.activity;

/**
 * Created by yuryandreev on 20/09/16.
 */
public enum ResponseType {
    NO_DATA_FOUND("404"),
    SUCCESS("200"),
    CAPTCHA_NUMBER_IS_NOT_VALID("201");

    private final String type;

    ResponseType(String type) {
        this.type = type;
    }

    public static ResponseType getByStatus(String status) {
        if(status == null || status.isEmpty()) {
            return null;
        }

        for (ResponseType responseType : values()) {
            if(responseType.getType().equals(status)) {
                return responseType;
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }
}
