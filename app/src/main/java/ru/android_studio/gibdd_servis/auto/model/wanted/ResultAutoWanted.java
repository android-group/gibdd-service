package ru.android_studio.gibdd_servis.auto.model.wanted;

import java.util.ArrayList;
import java.util.List;

import ru.android_studio.gibdd_servis.auto.model.ResultAuto;

/**
 * Created by yuryandreev on 24/09/16.
 */
public class ResultAutoWanted extends ResultAuto {
    List<Wanted> wantedList = new ArrayList<>();

    public List<Wanted> getWantedList() {
        return wantedList;
    }

    public void setWantedList(List<Wanted> wantedList) {
        this.wantedList = wantedList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ResultAutoWanted that = (ResultAutoWanted) o;

        return wantedList != null ? wantedList.equals(that.wantedList) : that.wantedList == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (wantedList != null ? wantedList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResultAutoWanted{" +
                "wantedList=" + wantedList +
                '}';
    }
}
