package ru.android_studio.gibdd_servis.auto.model.history;

/**
 * Created by yuryandreev on 20/09/16.
 */
public enum ResponseStatus {
    NO_DATA_FOUND("404", "Данные не найдены"),
    SUCCESS("200", "Успех"),
    BAD_REQUEST("BAD_REQUEST", "Что то пошло не так"),
    CAPTCHA_NOT_VALID("201", "Прошло слишком много времени с момента загрузки картинки, повторите попытку");

    private final String type;
    private final String text;

    ResponseStatus(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public static ResponseStatus getByStatus(String status) {
        if (status == null || status.isEmpty()) {
            return null;
        }

        for (ResponseStatus responseStatus : values()) {
            if (responseStatus.getType().equals(status)) {
                return responseStatus;
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
