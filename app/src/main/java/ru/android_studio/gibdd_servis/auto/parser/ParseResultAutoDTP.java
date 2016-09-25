package ru.android_studio.gibdd_servis.auto.parser;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import ru.android_studio.gibdd_servis.auto.model.dtp.Accidents;
import ru.android_studio.gibdd_servis.auto.model.dtp.ResultAutoDTP;

public class ParseResultAutoDTP extends ParseResultAuto<ResultAutoDTP> {

    private static final String TAG = "ParseResultAutoDTP";

    private ParseResultAutoDTP() {

    }

    public static ParseResultAutoDTP getInstance() {
        return new ParseResultAutoDTP();
    }

    @Nullable
    public static ResultAutoDTP parseResult(String resultText) {
        System.out.println("resultText: " + resultText);
        Log.d(TAG, "parse result: " + resultText);
        if (resultText == null || resultText.isEmpty()) {
            return null;
        }

        ResultAutoDTP result = new ResultAutoDTP();
        return result;
    }

    @Override
    public void mapSuccessResult(JsonObject requestResult, ResultAutoDTP result) {
        JsonArray accidents = requestResult.getAsJsonArray("Accidents");
        List<Accidents> list = new ArrayList<>();
        for (JsonElement accident : accidents) {
            Accidents item = new Accidents();
            list.add(item);
        }
        result.setAccidents(list);

        String errorDescription = requestResult.getAsJsonPrimitive("errorDescription").getAsString();
        result.setErrorDescription(errorDescription);
        String statusCode = requestResult.getAsJsonPrimitive("statusCode").getAsString();
        result.setStatusCode(statusCode);
    }

    @Override
    public void mapFailureResult(JsonObject jsonObject, ResultAutoDTP result) {

    }
}
