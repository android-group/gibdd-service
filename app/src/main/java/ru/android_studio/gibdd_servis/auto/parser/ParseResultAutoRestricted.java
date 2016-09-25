package ru.android_studio.gibdd_servis.auto.parser;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.JsonObject;

import ru.android_studio.gibdd_servis.auto.model.restricted.ResultAutoRestricted;

public class ParseResultAutoRestricted extends ParseResultAuto<ResultAutoRestricted> {

    private static final String TAG = "ParseResultAutoHistory";

    private ParseResultAutoRestricted() {

    }

    public static ParseResultAutoRestricted getInstance() {
        return new ParseResultAutoRestricted();
    }

    @Override
    public void mapSuccessResult(JsonObject jsonObject, ResultAutoRestricted result) {

    }

    @Override
    public void mapFailureResult(JsonObject jsonObject, ResultAutoRestricted result) {

    }
}
