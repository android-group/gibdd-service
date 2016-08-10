package ru.android_studio.gibdd_servis.auto.model.restrinct;

import java.util.ArrayList;
import java.util.List;

/*
    {"RequestResult":{"records":[],"count":0,"error":0},"vin":"00000000000000000000","status":200}
 */
public class RequestResult {

    // "records"
    private List<String> records = new ArrayList<>();
    // "count"
    private Integer count;
    // "error"
    private Integer error;

    public List<String> getRecords() {
        return records;
    }

    public void setRecords(List<String> records) {
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
