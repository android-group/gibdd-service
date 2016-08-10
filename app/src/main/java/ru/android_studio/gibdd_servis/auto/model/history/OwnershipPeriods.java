package ru.android_studio.gibdd_servis.auto.model.history;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnershipPeriods {

    // "ownershipPeriod"
    private List<OwnershipPeriod> ownershipPeriod = new ArrayList<OwnershipPeriod>();

    public List<OwnershipPeriod> getOwnershipPeriod() {
        return ownershipPeriod;
    }

    public void setOwnershipPeriod(List<OwnershipPeriod> ownershipPeriod) {
        this.ownershipPeriod = ownershipPeriod;
    }
}
