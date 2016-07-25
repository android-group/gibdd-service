package ru.android_studio.gibdd_servis.gibdd;

public enum CheckType {
    AUTO("auto"),
    DRIVER("driver");

    private final String title;

    CheckType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
