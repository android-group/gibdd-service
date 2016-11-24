package ru.android_studio.gibdd_servis.auto.gibdd;

public enum CheckAutoType {
    HISTORY("history", "Истории регистрации в ГИБДД"), // Проверка истории регистрации в ГИБДД
    WANTED("wanted", "Нахождения в розыске"), // Проверка нахождения в розыске
    RESTRICT("restrict", "Наличия ограничений"), // Проверка наличия ограничений
    DTP("dtp", "Участие в ДТП"); // Проверка на участие в дорожно-транспортных происшествиях

    private final String value;
    private final String titile;

    CheckAutoType(String value, String titile) {
        this.value = value;
        this.titile = titile;
    }

    public String getValue() {
        return value;
    }

    public String getTitile() {
        return titile;
    }
}
