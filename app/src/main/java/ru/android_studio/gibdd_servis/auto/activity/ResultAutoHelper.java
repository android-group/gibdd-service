package ru.android_studio.gibdd_servis.auto.activity;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuryandreev on 20/09/16.
 */
public class ResultAutoHelper {

    private static final String TAG = "ResultAutoHelper";

    private ResultAutoHelper() {

    }

    @Nullable
    public static ResultAutoObject parseResult(String resultText) {
        System.out.println("resultText: " + resultText);
        Log.d(TAG, "parse result: " + resultText);
        if (resultText == null || resultText.isEmpty()) {
            return null;
        }

        ResultAutoObject result = new ResultAutoObject();

        JsonObject jsonObject = new JsonParser().parse(resultText).getAsJsonObject();
        result.setResponse(resultText);

        mapResult(jsonObject, result);
        return result;
    }

    @Nullable
    public static ResultAutoObject parseSuccessResult(String resultText) {
        ResultAutoObject result = new ResultAutoObject();
        JsonObject jsonObject = new JsonParser().parse(resultText).getAsJsonObject();
        String status = getIfExists(jsonObject.getAsJsonPrimitive("status"));
        Log.i(TAG, "result status: " + status);
        ResponseType type = ResponseType.getByStatus(status);
        result.setType(type);
        mapSuccessResult(jsonObject, result);
        return result;
    }

    @Nullable
    public static ResultAutoObject parseFailureResult(String resultText) {
        ResultAutoObject result = new ResultAutoObject();
        JsonObject jsonObject = new JsonParser().parse(resultText).getAsJsonObject();
        String status = getIfExists(jsonObject.getAsJsonPrimitive("status"));
        Log.i(TAG, "result status: " + status);
        ResponseType type = ResponseType.getByStatus(status);
        result.setType(type);
        mapFailureResult(jsonObject, result);
        return result;
    }

    private static void mapResult(JsonObject source, ResultAutoObject target) {
        String status = getIfExists(source.getAsJsonPrimitive("status"));
        Log.i(TAG, "result status: " + status);

        ResponseType type = ResponseType.getByStatus(status);
        target.setType(type);
        System.out.println("type: " + type);
        /*if (type == ResponseType.SUCCESS) {
            mapSuccessResult(source, target);
        } else {
            mapFailureResult(source, target);
        }*/
    }

    public static void mapFailureResult(JsonObject source, ResultAutoObject target) {
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

    public static void mapSuccessResult(JsonObject source, ResultAutoObject target) {
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

    private static void mapVehiclePassport(JsonObject source, ResultAutoObject target) {
        Log.i(TAG, "parse vehicle passport");
        JsonObject vehicleJsonObject = source.getAsJsonObject("vehicle");
        if (vehicleJsonObject == null || vehicleJsonObject.isJsonNull()) {
            System.err.println("vehicle == null");
            Log.i(TAG, "vehicle Json is Null");
            return;
        }

        Vehicle vehicle = new Vehicle();

        String engineVolume = getIfExists(vehicleJsonObject.getAsJsonPrimitive("engineVolume"));
        vehicle.setColor(engineVolume);

        String engineNumber = getIfExists(vehicleJsonObject.getAsJsonPrimitive("engineNumber"));
        vehicle.setColor(engineNumber);

        String color = getIfExists(vehicleJsonObject.getAsJsonPrimitive("color"));
        vehicle.setColor(color);

        String bodyNumber = getIfExists(vehicleJsonObject.getAsJsonPrimitive("bodyNumber"));
        vehicle.setBodyNumber(bodyNumber);

        String year = getIfExists(vehicleJsonObject.getAsJsonPrimitive("year"));
        vehicle.setYear(year);

        String vin = getIfExists(vehicleJsonObject.getAsJsonPrimitive("vin"));
        vehicle.setVin(vin);

        String model = getIfExists(vehicleJsonObject.getAsJsonPrimitive("model"));
        vehicle.setModel(model);

        String category = getIfExists(vehicleJsonObject.getAsJsonPrimitive("category"));
        vehicle.setCategory(category);

        String strType = getIfExists(vehicleJsonObject.getAsJsonPrimitive("type"));
        AutoType type = AutoType.getByTypeNumberString(strType);
        vehicle.setType(type);

        String powerKwt = getIfExists(vehicleJsonObject.getAsJsonPrimitive("powerKwt"));
        vehicle.setPowerKwt(powerKwt);

        String powerHp = getIfExists(vehicleJsonObject.getAsJsonPrimitive("powerHp"));
        vehicle.setPowerHp(powerHp);

        target.setVehicle(vehicle);
    }

    static String getIfExists(JsonPrimitive engineVolume) {
        if(engineVolume == null || engineVolume.isJsonNull()) {
            return null;
        }
        return engineVolume.getAsString();
    }

    private static void mapOwnershipPeriod(JsonObject ownershipPeriods, ResultAutoObject target) {
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
            ownershipPeriod.setFrom(getIfExists(jsonElement.getAsJsonPrimitive("from")));
            ownershipPeriod.setTo(getIfExists(jsonElement.getAsJsonPrimitive("to")));
            ownershipPeriod.setSimplePersonType(TypeOwner.getByTypeNumberString(getIfExists(jsonElement.getAsJsonPrimitive("simplePersonType"))));
            ownershipPeriodList.add(ownershipPeriod);
        }
        target.setOwnershipPeriodList(ownershipPeriodList);
    }
}
