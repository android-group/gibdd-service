package ru.android_studio.gibdd_servis.auto.model.dtp.empty;

import java.util.HashMap;
import java.util.Map;

import ru.android_studio.gibdd_servis.auto.model.dtp.RequestResult;

/**
 * {"RequestResult":{"errorDescription":"","statusCode":1,"Accidents":[]},"vin":"00000000000000000000","status":200}
 */
public class NotFound {

    // "RequestResult"
    private RequestResult requestResult;
    // "vin"
    private String vin;
    // "status"
    private Integer status;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
