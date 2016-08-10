package ru.android_studio.gibdd_servis.auto.model.history;

/**
 * {"RequestResult":{"ownershipPeriods":{"ownershipPeriod":[{"simplePersonType":"Legal","from":"2003-02-25T00:00:00.000+03:00"}]},"vehiclePassport":{},"vehicle":{"chassisNumber":"0929281","engineVolume":"5000.0","color":"СВЕТЛО-ГОЛУБОЙ","bodyNumber":"00000000000000000000","year":"1986","engineNumber":"110980","vin":"00000000000000000000","model":"ГАЗ53А НЦ4.6","category":"С","type":"51","powerHp":"115"}},"vin":"00000000000000000000","regnum":null,"message":"ver.3.1","status":200}
 */
public class Example {

    // "RequestResult")
    private RequestResult requestResult;

    // "vin"
    private String vin;

    // "regnum"
    private Object regnum;

    // "message"
    private String message;

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

    public Object getRegnum() {
        return regnum;
    }

    public void setRegnum(Object regnum) {
        this.regnum = regnum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
