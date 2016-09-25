package ru.android_studio.gibdd_servis.auto.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import ru.android_studio.gibdd_servis.auto.model.wanted.ResultAutoWanted;
import ru.android_studio.gibdd_servis.auto.model.wanted.Wanted;

public class ParseResultAutoWanted extends ParseResultAuto<ResultAutoWanted> {

    private static final String TAG = "ParseResultAutoWanted";

    private ParseResultAutoWanted() {

    }

    public static ParseResultAutoWanted getInstance() {
        return new ParseResultAutoWanted();
    }

    @Override
    public void mapSuccessResult(JsonObject requestResult, ResultAutoWanted result) {
        ArrayList<Wanted> wantedList = new ArrayList<>();
        JsonArray records = requestResult.getAsJsonArray("records");
        for (JsonElement record : records) {
            JsonObject asJsonObject = record.getAsJsonObject();
            Wanted wanted = new Wanted();
            wanted.setW_data_pu(asJsonObject.getAsJsonPrimitive("w_data_pu").getAsString());
            wanted.setW_god_vyp(asJsonObject.getAsJsonPrimitive("w_god_vyp").getAsString());
            wanted.setW_model(asJsonObject.getAsJsonPrimitive("w_model").getAsString());
            wanted.setW_rec(asJsonObject.getAsJsonPrimitive("w_rec").getAsString());
            wanted.setW_reg_inic(asJsonObject.getAsJsonPrimitive("w_reg_inic").getAsString());
            wanted.setW_un_gic(asJsonObject.getAsJsonPrimitive("w_un_gic").getAsString());
            wanted.setW_user(asJsonObject.getAsJsonPrimitive("w_user").getAsString());
            wanted.setW_vid_uch(asJsonObject.getAsJsonPrimitive("w_vid_uch").getAsString());
            wantedList.add(wanted);
        }
        result.setWantedList(wantedList);
    }

    @Override
    public void mapFailureResult(JsonObject jsonObject, ResultAutoWanted result) {

    }
}
