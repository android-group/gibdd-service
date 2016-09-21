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

    @Override
    public String toString() {
        return "OwnershipPeriod{" +
                "id='" + id + '\'' +
                ", simplePersonType=" + simplePersonType +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnershipPeriod that = (OwnershipPeriod) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (simplePersonType != that.simplePersonType) return false;
        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        return to != null ? to.equals(that.to) : that.to == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (simplePersonType != null ? simplePersonType.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
