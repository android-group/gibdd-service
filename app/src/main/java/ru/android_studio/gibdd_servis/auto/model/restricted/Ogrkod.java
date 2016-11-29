package ru.android_studio.gibdd_servis.auto.model.restricted;

/**
 * Created by yuryandreev on 25/09/16.
 */
public enum Ogrkod {
    a0("0", ""),
    a1("1", "Запрет на регистрационные действия"),
    a2("2", "Запрет на снятие с учета"),
    a3("3", "Запрет на регистрационные действия и прохождение ГТО"),
    a4("4", "Утилизация (для транспорта не старше 5 лет)"),
    a5("5", "Аннулирование");

    private final String type;
    private final String numType;

    Ogrkod(String numType, String type) {
        this.numType = numType;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getNumType() {
        return numType;
    }

    public static Ogrkod getOgrkod(String numType) {
        if (numType == null || numType.isEmpty()) {
            return Ogrkod.a0;
        }

        for (Ogrkod ogrkod : values()) {
            if (ogrkod.getNumType().equals(numType)) {
                return ogrkod;
            }
        }
        return Ogrkod.a0;
    }
}
