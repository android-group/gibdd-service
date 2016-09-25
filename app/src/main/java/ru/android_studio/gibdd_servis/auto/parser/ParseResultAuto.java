package ru.android_studio.gibdd_servis.auto.parser;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import ru.android_studio.gibdd_servis.auto.model.ResultAuto;
import ru.android_studio.gibdd_servis.auto.model.history.ResponseStatus;

public abstract class ParseResultAuto<T extends ResultAuto> {
    private static final String TAG = "ParseResultAuto";

    @Nullable
    public static ResponseStatus getResponseStatus(String resultText) {
        System.out.println("resultText: " + resultText);
        Log.d(TAG, "parse result: " + resultText);
        if (resultText == null || resultText.isEmpty()) {
            return ResponseStatus.BAD_REQUEST;
        }

        JsonObject jsonObject = new JsonParser().parse(resultText).getAsJsonObject();
        String status = ParseResultAuto.getIfExists(jsonObject.getAsJsonPrimitive("status"));
        Log.i(TAG, "result status: " + status);

        return ResponseStatus.getByStatus(status);
    }

    static String getIfExists(JsonPrimitive engineVolume) {
        if (engineVolume == null || engineVolume.isJsonNull()) {
            return null;
        }
        return engineVolume.getAsString();
    }


    @Nullable
    public T mapSuccessResult(String resultText, T result) {
        result.setResponse(resultText);
        JsonObject jsonObject = new JsonParser().parse(resultText).getAsJsonObject();
        String status = ParseResultAuto.getIfExists(jsonObject.getAsJsonPrimitive("status"));
        Log.i(TAG, "result status: " + status);
        ResponseStatus type = ResponseStatus.getByStatus(status);
        result.setType(type);

        JsonObject requestResult = jsonObject.getAsJsonObject("RequestResult");

        JsonPrimitive error = requestResult.getAsJsonPrimitive("error");
        if(error != null && !error.isJsonNull()) {
            result.setError(error.getAsString());
        }

        JsonPrimitive count = requestResult.getAsJsonPrimitive("count");
        if (count != null && !count.isJsonNull()) {
            result.setCount(count.getAsString());
        }

        JsonPrimitive vin = jsonObject.getAsJsonPrimitive("vin");
        if (vin != null && !vin.isJsonNull()) {
            result.setVin(vin.getAsString());
        }

        mapSuccessResult(requestResult, result);
        return result;
    }

    @Nullable
    public T parseFailureResult(String resultText, T result) {
        JsonObject jsonObject = new JsonParser().parse(resultText).getAsJsonObject();
        String status = ParseResultAuto.getIfExists(jsonObject.getAsJsonPrimitive("status"));
        Log.i(TAG, "result status: " + status);
        ResponseStatus type = ResponseStatus.getByStatus(status);
        result.setType(type);
        mapFailureResult(jsonObject, result);
        return result;
    }

    public abstract void mapSuccessResult(JsonObject requestResult, T result);

    public abstract void mapFailureResult(JsonObject jsonObject, T result);
}
