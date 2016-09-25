package ru.android_studio.gibdd_servis.auto.response.dtp;

import com.google.gson.JsonObject;

import ru.android_studio.gibdd_servis.auto.model.dtp.ResultAutoDTP;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAuto;

public class ParseResultAutoDtpTest extends ParseResultAuto<ResultAutoDTP> {


    @Override
    public void mapSuccessResult(JsonObject jsonObject, ResultAutoDTP result) {

    }

    @Override
    public void mapFailureResult(JsonObject jsonObject, ResultAutoDTP result) {

    }
}