package ru.android_studio.gibdd_servis.auto.parser;

import com.google.gson.JsonObject;

import ru.android_studio.gibdd_servis.auto.model.wanted.ResultAutoWanted;

public class ParseResultAutoWanted extends ParseResultAuto<ResultAutoWanted> {

    private static final String TAG = "ParseResultAutoWanted";

    private ParseResultAutoWanted() {

    }

    public static ParseResultAutoWanted getInstance() {
        return new ParseResultAutoWanted();
    }

    @Override
    public void mapSuccessResult(JsonObject requestResult, ResultAutoWanted result) {

    }

    @Override
    public void mapFailureResult(JsonObject jsonObject, ResultAutoWanted result) {

    }
}
