package ru.android_studio.gibdd_servis.auto.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import ru.android_studio.gibdd_servis.auto.model.dtp.Accidents;
import ru.android_studio.gibdd_servis.auto.model.dtp.DamagePoints;
import ru.android_studio.gibdd_servis.auto.model.dtp.ResultAutoDtp;

public class ParseResultAutoDtp extends ParseResultAuto<ResultAutoDtp> {

    private static final String TAG = "ParseResultAutoDtp";

    private ParseResultAutoDtp() {

    }

    public static ParseResultAutoDtp getInstance() {
        return new ParseResultAutoDtp();
    }

    @Override
    public void mapSuccessResult(JsonObject requestResult, ResultAutoDtp result) {
        JsonArray accidents = requestResult.getAsJsonArray("Accidents");
        List<Accidents> list = new ArrayList<>();
        for (JsonElement accidentJsonElement : accidents) {
            Accidents itemAccidents = new Accidents();
            JsonObject accidentJsonObject = accidentJsonElement.getAsJsonObject();

            itemAccidents.setAccidentDateTime(accidentJsonObject.getAsJsonPrimitive("AccidentDateTime").getAsString());
            itemAccidents.setAccidentNumber(accidentJsonObject.getAsJsonPrimitive("AccidentNumber").getAsString());
            itemAccidents.setAccidentType(accidentJsonObject.getAsJsonPrimitive("AccidentType").getAsString());
            itemAccidents.setRegionName(accidentJsonObject.getAsJsonPrimitive("RegionName").getAsString());
            itemAccidents.setVehicleDamageState(accidentJsonObject.getAsJsonPrimitive("VehicleDamageState").getAsString());
            itemAccidents.setVehicleMark(accidentJsonObject.getAsJsonPrimitive("VehicleMark").getAsString());
            itemAccidents.setVehicleModel(accidentJsonObject.getAsJsonPrimitive("VehicleModel").getAsString());
            itemAccidents.setVehicleYear(accidentJsonObject.getAsJsonPrimitive("VehicleYear").getAsString());

            JsonArray damagePointsArray = accidentJsonObject.getAsJsonArray("DamagePoints");
            if(damagePointsArray != null && !damagePointsArray.isJsonNull()) {
                ArrayList<DamagePoints> damagePoints = new ArrayList<>();
                for (JsonElement damagePointElement : damagePointsArray) {
                    DamagePoints item = new DamagePoints();
                    damagePoints.add(item);
                }
                itemAccidents.setDamagePoints(damagePoints);
            }

            list.add(itemAccidents);
        }
        result.setAccidents(list);

        String errorDescription = requestResult.getAsJsonPrimitive("errorDescription").getAsString();
        result.setErrorDescription(errorDescription);
        String statusCode = requestResult.getAsJsonPrimitive("statusCode").getAsString();
        result.setStatusCode(statusCode);
    }

    @Override
    public void mapFailureResult(JsonObject jsonObject, ResultAutoDtp result) {

    }
}
