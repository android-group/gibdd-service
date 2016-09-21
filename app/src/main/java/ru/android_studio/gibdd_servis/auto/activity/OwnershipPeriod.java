package ru.android_studio.gibdd_servis.auto.activity;

/**
 * Период владения транспортным средством
 */
public class OwnershipPeriod {
    private String id;
    private TypeOwner simplePersonType;
    private String from;
    private String to;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeOwner getSimplePersonType() {
        return simplePersonType;
    }

    public void setSimplePersonType(TypeOwner simplePersonType) {
        this.simplePersonType = simplePersonType;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
