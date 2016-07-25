package ru.android_studio.gibdd_servis.car.gibdd;

public enum CheckAutoType {
    HISTORY("history"), // Проверка истории регистрации в ГИБДД
    WANTED("wanted"), // Проверка нахождения в розыске
    RESTRICT("restrict"), // Проверка наличия ограничений
    DTP("dtp"); // Проверка на участие в дорожно-транспортных происшествиях

    private final String value;

    CheckAutoType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
