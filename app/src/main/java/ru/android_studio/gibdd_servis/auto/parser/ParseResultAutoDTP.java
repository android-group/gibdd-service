package ru.android_studio.gibdd_servis.auto.parser;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.JsonObject;

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

    }

    @Override
    public void mapFailureResult(JsonObject jsonObject, ResultAutoDTP result) {

    }
}
