package ru.android_studio.gibdd_servis.auto.model.restrinct;

public class NotFound {
    // "RequestResult"
    private RequestResult requestResult;
    // "vin"
    private String vin;
    // "status"
    private Integer status;

    public RequestResult getRequestResult() {
        return requestResult;
    }

    public void setRequestResult(RequestResult requestResult) {
        this.requestResult = requestResult;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
