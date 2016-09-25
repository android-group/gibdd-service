package ru.android_studio.gibdd_servis.auto.model.dtp;

import java.util.ArrayList;
import java.util.List;

import ru.android_studio.gibdd_servis.auto.model.Accidents;
import ru.android_studio.gibdd_servis.auto.model.ResultAuto;

/**
 * Created by yuryandreev on 24/09/16.
 */
public class ResultAutoDTP extends ResultAuto {
    List<Accidents> accidents = new ArrayList<>();
    String statusCode;
    String errorDescription;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public List<Accidents> getAccidents() {
        return accidents;
    }

    public void setAccidents(List<Accidents> accidents) {
        this.accidents = accidents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ResultAutoDTP that = (ResultAutoDTP) o;

        if (accidents != null ? !accidents.equals(that.accidents) : that.accidents != null)
            return false;
        if (statusCode != null ? !statusCode.equals(that.statusCode) : that.statusCode != null)
            return false;
        return errorDescription != null ? errorDescription.equals(that.errorDescription) : that.errorDescription == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (accidents != null ? accidents.hashCode() : 0);
        result = 31 * result + (statusCode != null ? statusCode.hashCode() : 0);
        result = 31 * result + (errorDescription != null ? errorDescription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResultAutoDTP{" +
                "accidents=" + accidents +
                ", statusCode='" + statusCode + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                '}';
    }
}
