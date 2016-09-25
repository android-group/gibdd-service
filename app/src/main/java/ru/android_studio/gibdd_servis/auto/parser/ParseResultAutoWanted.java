package ru.android_studio.gibdd_servis.auto.parser;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.JsonObject;

import ru.android_studio.gibdd_servis.auto.model.ResultAuto;
import ru.android_studio.gibdd_servis.auto.model.restricted.ResultAutoRestricted;
import ru.android_studio.gibdd_servis.auto.model.wanted.ResultAutoWanted;

public class ParseResultAutoWanted extends ParseResultAuto<ResultAutoRestricted> {

    private static final String TAG = "ParseResultAutoHistory";

    private ParseResultAutoWanted() {

    }

    public static ParseResultAutoWanted getInstance() {
        return new ParseResultAutoWanted();
    }

    @Nullable
    public static ResultAutoWanted parseResult(String resultText) {
        System.out.println("resultText: " + resultText);
        Log.d(TAG, "parse result: " + resultText);
        if (resultText == null || resultText.isEmpty()) {
            return null;
        }

        ResultAutoWanted result = new ResultAutoWanted();

        return result;
    }

    @Override
    public void mapSuccessResult(JsonObject jsonObject, ResultAutoRestricted result) {

    }

    @Override
    public void mapFailureResult(JsonObject jsonObject, ResultAutoRestricted result) {

    }
}
