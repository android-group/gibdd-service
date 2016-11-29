package ru.android_studio.gibdd_servis.auto.model.restricted;

/**
 * Created by yuryandreev on 25/09/16.
 */
public enum Organs {
    a0("0", "не предусмотренный код"),
    a1("1", "Судебные органы"),
    a2("2", "Судебный пристав"),
    a3("3", "Таможенные органы"),
    a4("4", "Органы социальной защиты"),
    a5("5", "Нотариус"),
    a6("6", "ОВД или иные правоохр. органы"),
    a7("7", "ОВД или иные правоохр. органы (прочие)");

    private final String type;
    private final String text;

    Organs(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public static Organs getDivtype(String type) {
        if (type == null || type.isEmpty()) {
            return a0;
        }
        for (Organs organs : values()) {
            if (organs.getType().equals(type)) {
                return organs;
            }
        }
        return a0;
    }
}
