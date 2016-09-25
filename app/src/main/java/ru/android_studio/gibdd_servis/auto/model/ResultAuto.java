package ru.android_studio.gibdd_servis.auto.model;

import ru.android_studio.gibdd_servis.auto.model.history.ResponseStatus;

/**
 * Created by yuryandreev on 25/09/16.
 */
public class ResultAuto {

    private String vin;
    private String count;
    private String error;
    protected String response;
    protected ResponseStatus type;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ResponseStatus getType() {
        return type;
    }

    public void setType(ResponseStatus type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultAuto that = (ResultAuto) o;

        if (vin != null ? !vin.equals(that.vin) : that.vin != null) return false;
        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (error != null ? !error.equals(that.error) : that.error != null) return false;
        if (response != null ? !response.equals(that.response) : that.response != null)
            return false;
        return type == that.type;

    }

    @Override
    public int hashCode() {
        int result = vin != null ? vin.hashCode() : 0;
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (error != null ? error.hashCode() : 0);
        result = 31 * result + (response != null ? response.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResultAuto{" +
                "vin='" + vin + '\'' +
                ", count='" + count + '\'' +
                ", error='" + error + '\'' +
                ", response='" + response + '\'' +
                ", type=" + type +
                '}';
    }
}
