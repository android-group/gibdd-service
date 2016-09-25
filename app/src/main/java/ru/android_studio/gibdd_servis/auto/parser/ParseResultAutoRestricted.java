package ru.android_studio.gibdd_servis.auto.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

import ru.android_studio.gibdd_servis.auto.model.restricted.RestrictedItem;
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
        JsonObject requestResult = jsonObject.getAsJsonObject("RequestResult");

        JsonPrimitive error = requestResult.getAsJsonPrimitive("error");
        result.setError(error.getAsString());

        JsonPrimitive count = requestResult.getAsJsonPrimitive("count");
        result.setCount(count.getAsString());

        List<RestrictedItem> list = new ArrayList<>();
        JsonArray records = requestResult.getAsJsonArray("records");

        for (JsonElement record : records) {
            RestrictedItem item = new RestrictedItem();
            JsonObject itemJsonObject = record.getAsJsonObject();

            JsonPrimitive regname = itemJsonObject.getAsJsonPrimitive("regname");
            item.setRegname(regname.getAsString());

            JsonPrimitive gid = itemJsonObject.getAsJsonPrimitive("gid");
            item.setGid(gid.getAsString());

            JsonPrimitive tsyear = itemJsonObject.getAsJsonPrimitive("tsyear");
            item.setTsyear(tsyear.getAsString());

            JsonPrimitive dateadd = itemJsonObject.getAsJsonPrimitive("dateadd");
            item.setDateadd(dateadd.getAsString());

            JsonPrimitive regid = itemJsonObject.getAsJsonPrimitive("regid");
            item.setRegid(regid.getAsString());

            JsonPrimitive divtype = itemJsonObject.getAsJsonPrimitive("divtype");
            item.setDivtype(divtype.getAsString());

            JsonPrimitive dateogr = itemJsonObject.getAsJsonPrimitive("dateogr");
            item.setDateogr(dateogr.getAsString());

            JsonPrimitive ogrkod = itemJsonObject.getAsJsonPrimitive("ogrkod");
            item.setOgrkod(ogrkod.getAsString());

            JsonPrimitive divid = itemJsonObject.getAsJsonPrimitive("divid");
            item.setDivid(divid.getAsString());

            JsonPrimitive tsmodel = itemJsonObject.getAsJsonPrimitive("tsmodel");
            item.setTsmodel(tsmodel.getAsString());

            list.add(item);
        }
        result.setRestrictedItem(list);

        result.setVin(jsonObject.getAsJsonPrimitive("vin").getAsString());
    }

    @Override
    public void mapFailureResult(JsonObject jsonObject, ResultAutoRestricted result) {

    }
}
