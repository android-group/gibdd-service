package ru.android_studio.gibdd_servis.auto.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

import ru.android_studio.gibdd_servis.auto.model.restricted.Ogrkod;
import ru.android_studio.gibdd_servis.auto.model.restricted.Organs;
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
    public void mapSuccessResult(JsonObject requestResult, ResultAutoRestricted result) {
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
            item.setDivtype(Organs.getDivtype(divtype.getAsString()));

            JsonPrimitive dateogr = itemJsonObject.getAsJsonPrimitive("dateogr");
            item.setDateogr(dateogr.getAsString());

            JsonPrimitive ogrkod = itemJsonObject.getAsJsonPrimitive("ogrkod");
            item.setOgrkod(Ogrkod.getOgrkod(ogrkod.getAsString()));

            JsonPrimitive divid = itemJsonObject.getAsJsonPrimitive("divid");
            item.setDivid(divid.getAsString());

            JsonPrimitive tsmodel = itemJsonObject.getAsJsonPrimitive("tsmodel");
            item.setTsmodel(tsmodel.getAsString());

            list.add(item);
        }
        result.setRestrictedItem(list);
    }

    @Override
    public void mapFailureResult(JsonObject jsonObject, ResultAutoRestricted result) {

    }
}
