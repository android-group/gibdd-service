package ru.android_studio.gibdd_servis.auto.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.ActivityWithMenu;
import ru.android_studio.gibdd_servis.R;


public class ResultAutoActivity extends ActivityWithMenu {

    @BindView(R.id.result_text_view)
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_result);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        parseResult(extras.getString("result_text"));

        addToolbarByIconId(R.mipmap.ic_auto);
        setMenuConfig();

    }

    private void parseResult(String resultText) {
        // parse result text
        JsonObject jsonObject = new JsonParser().parse(resultText).getAsJsonObject();

        // if error show message
        JsonPrimitive message = jsonObject.getAsJsonPrimitive("message");
        if(!message.isJsonNull()) {
            resultTextView.setText(message.getAsString());
            return;
        }

        if ("200".equals(jsonObject.getAsJsonPrimitive("status").getAsString())) {
            parseSuccessResult(jsonObject);
        }
    }

    private void parseSuccessResult(JsonObject jsonObject) {
        JsonObject requestResult = jsonObject.getAsJsonObject("RequestResult");
        parseOwnershipPeriod(requestResult);
        parseVehiclePassport(requestResult);
    }


    @BindView(R.id.color)
    TextView colorTextView;

    @BindView(R.id.body_number)
    TextView bodyNumberTextView;

    @BindView(R.id.year)
    TextView yearTextView;

    @BindView(R.id.vin)
    TextView vinTextView;

    @BindView(R.id.model)
    TextView modelTextView;

    @BindView(R.id.category)
    TextView categoryTextView;

    @BindView(R.id.type)
    TextView typeTextView;

    @BindView(R.id.power_kwt)
    TextView powerKwtTextView;

    private void parseVehiclePassport(JsonObject requestResult) {
        JsonObject vehicle = requestResult.getAsJsonObject("vehicle");
        colorTextView.setText(vehicle.getAsJsonPrimitive("color").getAsString());
        bodyNumberTextView.setText(vehicle.getAsJsonPrimitive("bodyNumber").getAsString());
        yearTextView.setText(vehicle.getAsJsonPrimitive("year").getAsString());
        vinTextView.setText(vehicle.getAsJsonPrimitive("vin").getAsString());
        modelTextView.setText(vehicle.getAsJsonPrimitive("model").getAsString());
        categoryTextView.setText(vehicle.getAsJsonPrimitive("category").getAsString());
        typeTextView.setText(vehicle.getAsJsonPrimitive("type").getAsString());
        powerKwtTextView.setText(vehicle.getAsJsonPrimitive("powerKwt").getAsString());
    }

    private void parseOwnershipPeriod(JsonObject requestResult) {
        JsonArray ownershipPeriod = requestResult.getAsJsonObject("ownershipPeriods").getAsJsonArray("ownershipPeriod");
    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_car_btn;
    }
}
