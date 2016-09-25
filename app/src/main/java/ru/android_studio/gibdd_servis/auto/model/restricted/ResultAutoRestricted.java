package ru.android_studio.gibdd_servis.auto.model.restricted;

import java.util.List;

import ru.android_studio.gibdd_servis.auto.model.ResultAuto;

/**
 * Created by yuryandreev on 24/09/16.
 */
public class ResultAutoRestricted extends ResultAuto {
    private String vin;
    private String count;
    private String error;
    private List<RestrictedItem> restrictedItem;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public List<RestrictedItem> getRestrictedItem() {
        return restrictedItem;
    }

    public void setRestrictedItem(List<RestrictedItem> restrictedItem) {
        this.restrictedItem = restrictedItem;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultAutoRestricted that = (ResultAutoRestricted) o;

        if (vin != null ? !vin.equals(that.vin) : that.vin != null) return false;
        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (error != null ? !error.equals(that.error) : that.error != null) return false;
        return restrictedItem != null ? restrictedItem.equals(that.restrictedItem) : that.restrictedItem == null;

    }

    @Override
    public int hashCode() {
        int result = vin != null ? vin.hashCode() : 0;
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (error != null ? error.hashCode() : 0);
        result = 31 * result + (restrictedItem != null ? restrictedItem.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResultAutoRestricted{" +
                "vin='" + vin + '\'' +
                ", count='" + count + '\'' +
                ", error='" + error + '\'' +
                ", restrictedItem=" + restrictedItem +
                '}';
    }
}
