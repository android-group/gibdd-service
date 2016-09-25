package ru.android_studio.gibdd_servis.auto.model.restricted;

import java.util.List;

import ru.android_studio.gibdd_servis.auto.model.ResultAuto;
import ru.android_studio.gibdd_servis.auto.model.history.ResponseStatus;

/**
 * Created by yuryandreev on 24/09/16.
 */
public class ResultAutoRestricted extends ResultAuto {
    private String vin;
    private List<RestrictedItem> restrictedItem;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
