package ru.android_studio.gibdd_servis.driver.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.ActivityWithToolbar;
import ru.android_studio.gibdd_servis.R;


public class ResultDriverActivity extends ActivityWithToolbar {

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

    }
}
