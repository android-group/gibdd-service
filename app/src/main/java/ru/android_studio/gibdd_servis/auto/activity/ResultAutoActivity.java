package ru.android_studio.gibdd_servis.auto.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.ActivityWithMenu;
import ru.android_studio.gibdd_servis.R;


public class ResultAutoActivity extends ActivityWithMenu {

    @BindView(R.id.result_text_view)
    TextView resultTextView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_result);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        ResultAutoObject result = ResultAutoHelper.parseResult(extras.getString("result_text"));
        if (result == null) {
            Log.d(TAG, "result is empty");
            resultTextView.setText("Данные не найдены");
        }

        addToolbarByIconId(R.mipmap.ic_auto);

    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_car_btn;
    }
}
