package ru.android_studio.gibdd_servis.auto.model.history;

/**
 * Created by yuryandreev on 20/09/16.
 */
public enum TypeOwner {
    NATURAL("Natural", "Физическое лицо"),
    LEGAL("Legal", "Юридическое лицо");

    public static TypeOwner getByTypeNumberString(String typeNum) {
        if (typeNum == null || typeNum.isEmpty()) {
            return null;
        }

        for (TypeOwner autoType : values()) {
            if (autoType.getTypeNum().equals(typeNum))
                return autoType;
        }
        return null;
    }

    private final String typeNum;
    private final String text;

    TypeOwner(String typeNum, String text) {
        this.typeNum = typeNum;
        this.text = text;
    }

    public String getTypeNum() {
        return typeNum;
    }

    public String getText() {
        return text;
    }
}
