package ru.android_studio.gibdd_servis.driver.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.ActivityWithToolbar;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.driver.gibdd.RequestDriverAsyncTask;


public class ResultDriverActivity extends ActivityWithToolbar {

    @BindView(R.id.result_text_view)
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_result);

        ButterKnife.bind(this);

        addToolbarByIconId(R.mipmap.ic_driver);

        Bundle extras = getIntent().getExtras();
        String resultText = extras.getString(RequestDriverAsyncTask.RESULT_TEXT);
        JsonElement resultTextJsonElement = new JsonParser().parse(resultText);

        JsonObject jsonObject = resultTextJsonElement.getAsJsonObject();
        JsonPrimitive message = jsonObject.getAsJsonPrimitive("message");
        if (message != null && !message.isJsonNull()) {
            resultTextView.setText(message.getAsString());
        }


    }
}
