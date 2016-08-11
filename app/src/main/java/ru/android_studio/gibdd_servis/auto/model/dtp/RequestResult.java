package ru.android_studio.gibdd_servis.auto.model.dtp;

import java.util.ArrayList;
import java.util.List;

public class RequestResult {

    // "errorDescription"
    private String errorDescription;
    // "statusCode"
    private Integer statusCode;
    // "Accidents"
    private List<Object> accidents = new ArrayList<Object>();
}
