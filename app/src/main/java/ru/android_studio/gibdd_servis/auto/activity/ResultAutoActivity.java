package ru.android_studio.gibdd_servis.auto.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.ActivityWithMenu;
import ru.android_studio.gibdd_servis.ActivityWithMenuAndOCR;
import ru.android_studio.gibdd_servis.R;


public class ResultAutoActivity extends ActivityWithMenu {

    @BindView(R.id.result_text_view)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_result);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        String resultText = extras.getString("result_text");

        // parse result text
        JsonObject jsonObject = new JsonParser().parse(resultText).getAsJsonObject();

        JsonPrimitive message = jsonObject.getAsJsonPrimitive("message");
        if(!message.isJsonNull()) {
            textView.setText(message.getAsString());
        }

        textView.setText(resultText);


        addToolbarByIconId(R.mipmap.ic_auto);
        setMenuConfig();

    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_car_btn;
    }
}
