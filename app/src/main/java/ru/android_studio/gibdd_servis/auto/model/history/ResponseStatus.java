package ru.android_studio.gibdd_servis.auto.model.history;

/**
 * Created by yuryandreev on 20/09/16.
 */
public enum ResponseStatus {
    RESULT_HISTORY_404("404", "По указанному VIN не найдена информация о регистрации транспортного средства."),
    RESULT_HISTORY_403("403", "Информация по указанному VIN не может быть предоставлена."),
    RESULT_HISTORY_NULL("resultDescHistoryNull", "При получении ответа сервера произошла ошибка."),
    RESULT_RESTRICTED_NULL("resultDescRestrictedNull", "По указанному VIN (номер кузова или шасси) не найдена информация о наложении ограничений на регистрационные действия с транспортным средством."),
    RESULT_WANTED_NULL("resultDescWantedNull", "По указанному VIN (номер кузова или шасси) не найдена информация о розыске транспортного средства."),
    RESULT_AIUSDTP_NULL("resultDescAiusdtpNull", "В результате обработки запроса к АИУС ГИБДД записи о дорожно-транспортных происшествиях не найдены."),
    SUCCESS("200", "Успех"),
    BAD_REQUEST("BAD_REQUEST", "Что то пошло не так"),
    CAPTCHA_NOT_VALID("201", "Прошло слишком много времени с момента загрузки картинки, повторите попытку");
    /**
     * resultDescription: 'Проверка проведена ',
     * resultDescriptionStop: 'Работа сервиса временно приостановлена. Отложите, пожалуйста проверку на другое время.',
     * resultDescriptionFail: 'Проверка завершилась ошибкой на стороне сервера.',
     * resultDescAiusdtpNull: 'В результате обработки запроса к АИУС ГИБДД записи о дорожно-транспортных происшествиях не найдены.',
     * resultDescHistoryNull: 'При получении ответа сервера произошла ошибка.',
     * resultDescHistory403: 'Информация по указанному VIN не может быть предоставлена.',
     * resultDescHistory404: 'По указанному VIN не найдена информация о регистрации транспортного средства.',
     * resultDescWantedNull: 'По указанному VIN (номер кузова или шасси) не найдена информация о розыске транспортного средства.',
     * resultDescRestrictedNull: 'По указанному VIN (номер кузова или шасси) не найдена информация о наложении ограничений на регистрационные действия с транспортным средством.',
     * errorCaptcha: 'Цифры, изображенные на картинке, были введены неверно.',
     * progressBar:'<span>Выполняется запрос, ждите...&nbsp;&nbsp;<img src="/bitrix/js/main/core/images/wait.gif" title="Ожидание результатов запроса" /></span>',
     */
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
