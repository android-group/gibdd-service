package ru.android_studio.gibdd_servis.driver.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.ActivityWithMenu;
import ru.android_studio.gibdd_servis.ActivityWithMenuAndOCR;
import ru.android_studio.gibdd_servis.R;


public class ResultDriverActivity extends ActivityWithMenu {

    @BindView(R.id.result_text_view)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_result);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        JsonObject jsonObject = new JsonParser().parse(extras.getString("result_text")).getAsJsonObject();
        textView.setText(jsonObject.getAsJsonPrimitive("message").getAsString());

        addToolbarByIconId(R.mipmap.ic_driver);
        setMenuConfig();

    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_car_btn;
    }
}
