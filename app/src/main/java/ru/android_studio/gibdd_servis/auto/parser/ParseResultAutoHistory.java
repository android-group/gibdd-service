package ru.android_studio.gibdd_servis.auto.parser;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

import ru.android_studio.gibdd_servis.auto.model.history.AutoType;
import ru.android_studio.gibdd_servis.auto.model.history.OwnershipPeriod;
import ru.android_studio.gibdd_servis.auto.model.history.ResultAutoHistory;
import ru.android_studio.gibdd_servis.auto.model.history.TypeOwner;
import ru.android_studio.gibdd_servis.auto.model.history.Vehicle;

public class ParseResultAutoHistory extends ParseResultAuto<ResultAutoHistory> {

    private static final String TAG = "ParseResultAutoHistory";

    private ParseResultAutoHistory() {

    }

    public static ParseResultAutoHistory getInstance() {
        return new ParseResultAutoHistory();
    }

    public void mapFailureResult(JsonObject source, ResultAutoHistory target) {
        JsonPrimitive message = source.getAsJsonPrimitive("message");
        if (message == null || message.isJsonNull()) {
            System.err.println("message is null");
            return;
        }

        String strMessage = message.getAsString();
        Log.i(TAG, "result message: " + strMessage);
        //resultTextView.setText(strMessage);
        target.setMessage(strMessage);
    }

    public void mapSuccessResult(JsonObject source, ResultAutoHistory target) {
        Log.i(TAG, "parse success result");
        JsonObject requestResult = source.getAsJsonObject("RequestResult");
        if (requestResult == null || requestResult.isJsonNull()) {
            System.err.println("requestResult == null");
            Log.i(TAG, "Request result is null");
            return;
        }

        JsonObject ownershipPeriods = requestResult.getAsJsonObject("ownershipPeriods");
        if (ownershipPeriods == null || ownershipPeriods.isJsonNull()) {
            System.err.println("ownershipPeriods == null");
            return;
        }
        mapOwnershipPeriod(ownershipPeriods, target);
        mapVehiclePassport(requestResult, target);
    }

    private static void mapVehiclePassport(JsonObject source, ResultAutoHistory target) {
        Log.i(TAG, "parse vehicle passport");
        JsonObject vehicleJsonObject = source.getAsJsonObject("vehicle");
        if (vehicleJsonObject == null || vehicleJsonObject.isJsonNull()) {
            System.err.println("vehicle == null");
            Log.i(TAG, "vehicle Json is Null");
            return;
        }

        Vehicle vehicle = new Vehicle();

        String engineVolume = ParseResultAuto.getIfExists(vehicleJsonObject.getAsJsonPrimitive("engineVolume"));
        vehicle.setColor(engineVolume);

        String engineNumber = ParseResultAuto.getIfExists(vehicleJsonObject.getAsJsonPrimitive("engineNumber"));
        vehicle.setColor(engineNumber);

        String color = ParseResultAuto.getIfExists(vehicleJsonObject.getAsJsonPrimitive("color"));
        vehicle.setColor(color);

        String bodyNumber = ParseResultAuto.getIfExists(vehicleJsonObject.getAsJsonPrimitive("bodyNumber"));
        vehicle.setBodyNumber(bodyNumber);

        String year = ParseResultAuto.getIfExists(vehicleJsonObject.getAsJsonPrimitive("year"));
        vehicle.setYear(year);

        String vin = ParseResultAuto.getIfExists(vehicleJsonObject.getAsJsonPrimitive("vin"));
        vehicle.setVin(vin);

        String model = ParseResultAuto.getIfExists(vehicleJsonObject.getAsJsonPrimitive("model"));
        vehicle.setModel(model);

        String category = ParseResultAuto.getIfExists(vehicleJsonObject.getAsJsonPrimitive("category"));
        vehicle.setCategory(category);

        String strType = ParseResultAuto.getIfExists(vehicleJsonObject.getAsJsonPrimitive("type"));
        AutoType type = AutoType.getByTypeNumberString(strType);
        vehicle.setType(type);

        String powerKwt = ParseResultAuto.getIfExists(vehicleJsonObject.getAsJsonPrimitive("powerKwt"));
        vehicle.setPowerKwt(powerKwt);

        String powerHp = ParseResultAuto.getIfExists(vehicleJsonObject.getAsJsonPrimitive("powerHp"));
        vehicle.setPowerHp(powerHp);

        target.setVehicle(vehicle);
    }

    private static void mapOwnershipPeriod(JsonObject ownershipPeriods, ResultAutoHistory target) {
        Log.i(TAG, "parse ownership period");
        JsonArray ownershipPeriodsAsJsonArray = ownershipPeriods.getAsJsonArray("ownershipPeriod");
        if (ownershipPeriodsAsJsonArray == null || ownershipPeriodsAsJsonArray.isJsonNull() || ownershipPeriodsAsJsonArray.size() < 1) {
            System.err.println("ownershipPeriod == null");
            return;
        }
        List<OwnershipPeriod> ownershipPeriodList = new ArrayList<>(ownershipPeriodsAsJsonArray.size());
        for (int i = 0; i < ownershipPeriodsAsJsonArray.size(); i++) {
            JsonObject jsonElement = (JsonObject) ownershipPeriodsAsJsonArray.get(i);
            OwnershipPeriod ownershipPeriod = new OwnershipPeriod();
            ownershipPeriod.setId(String.valueOf(i));
            ownershipPeriod.setFrom(ParseResultAuto.getIfExists(jsonElement.getAsJsonPrimitive("from")));
            ownershipPeriod.setTo(ParseResultAuto.getIfExists(jsonElement.getAsJsonPrimitive("to")));
            ownershipPeriod.setSimplePersonType(TypeOwner.getByTypeNumberString(ParseResultAuto.getIfExists(jsonElement.getAsJsonPrimitive("simplePersonType"))));
            ownershipPeriodList.add(ownershipPeriod);
        }
        target.setOwnershipPeriodList(ownershipPeriodList);
    }
}
