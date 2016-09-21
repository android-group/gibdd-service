package ru.android_studio.gibdd_servis.auto.activity;

/**
 * Created by yuryandreev on 20/09/16.
 */
public enum ResponseType {
    NO_DATA_FOUND("404", "Данные не найдены"),
    SUCCESS("200", "Успех"),
    CAPTCHA_NUMBER_IS_NOT_VALID("201", "Прошло слишком много времени с момента загрузки картинки, повторите попытку");

    private final String type;
    private final String text;

    ResponseType(String type, String text) {
        this.type = type;
        this.text = text;
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

    public String getText() {
        return text;
    }
}
