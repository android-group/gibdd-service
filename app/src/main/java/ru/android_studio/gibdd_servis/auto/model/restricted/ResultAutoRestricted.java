package ru.android_studio.gibdd_servis.auto.model.restricted;

import java.util.List;

import ru.android_studio.gibdd_servis.auto.model.ResultAuto;

/**
 * Created by yuryandreev on 24/09/16.
 */
public class ResultAutoRestricted extends ResultAuto {
    private List<RestrictedItem> restrictedItem;

    public List<RestrictedItem> getRestrictedItem() {
        return restrictedItem;
    }

    public void setRestrictedItem(List<RestrictedItem> restrictedItem) {
        this.restrictedItem = restrictedItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ResultAutoRestricted that = (ResultAutoRestricted) o;

        return restrictedItem != null ? restrictedItem.equals(that.restrictedItem) : that.restrictedItem == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (restrictedItem != null ? restrictedItem.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "ResultAutoRestricted{" +
                "restrictedItem=" + restrictedItem +
                '}';
    }
}
