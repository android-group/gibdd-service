package ru.android_studio.gibdd_servis.auto.gibdd;

import ru.android_studio.gibdd_servis.R;

public enum CheckAutoType {
    HISTORY("history", "Истории регистрации", R.string.banner_history), // Проверка истории регистрации в ГИБДД
    WANTED("wanted", "Нахождения в розыске", R.string.banner_wanted), // Проверка нахождения в розыске
    RESTRICT("restrict", "Наличия ограничений", R.string.banner_restrict), // Проверка наличия ограничений
    DTP("dtp", "Участие в ДТП", R.string.banner_dtp); // Проверка на участие в дорожно-транспортных происшествиях

    private final String value;
    private final String title;
    private final int adUnitId;

    CheckAutoType(String value, String title, int adUnitId) {
        this.value = value;
        this.title = title;
        this.adUnitId = adUnitId;
    }

    public String getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }


    public int getAdUnitId() {
        return adUnitId;
    }
}
