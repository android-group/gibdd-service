package ru.android_studio.gibdd_servis.auto.gibdd;

public enum CheckAutoType {
    HISTORY("history", "Проверка истории регистрации в ГИБДД"), // Проверка истории регистрации в ГИБДД
    WANTED("wanted", "Проверка нахождения в розыске"), // Проверка нахождения в розыске
    RESTRICT("restrict", "Проверка наличия ограничений"), // Проверка наличия ограничений
    DTP("dtp", "Проверка на участие в ДТП"); // Проверка на участие в дорожно-транспортных происшествиях

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
