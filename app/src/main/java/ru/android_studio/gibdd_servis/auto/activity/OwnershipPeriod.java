package ru.android_studio.gibdd_servis.auto.activity;

/**
 * Created by yuryandreev on 20/09/16.
 */
public class OwnershipPeriod {
    private TypeOwner simplePersonType;
    private String from;
    private String to;

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
