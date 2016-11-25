package ru.android_studio.gibdd_servis.auto.model.wanted;

import java.util.ArrayList;
import java.util.List;

/**
 * {"RequestResult":{"records":[],"count":0,"error":0},"vin":"00000000000000000000","status":200}
 */
public class RequestResult {

    // "records")
    private List<Object> records = new ArrayList<Object>();
    // "count")
    private Integer count;
    // "error")
    private Integer error;

    public List<Object> getRecords() {
        return records;
    }

    public void setRecords(List<Object> records) {
        this.records = records;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }
}
