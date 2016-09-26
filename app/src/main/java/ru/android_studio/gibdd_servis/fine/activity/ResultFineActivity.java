package ru.android_studio.gibdd_servis.fine.activity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.ActivityWithMenuAndOCR;
import ru.android_studio.gibdd_servis.R;


public class ResultFineActivity extends ActivityWithMenuAndOCR {

    @BindView(R.id.result_text_view)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_history_result);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        textView.setText(extras.getString("result_text"));

        addToolbarByIconId(R.mipmap.ic_fine);
    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_car_btn;
    }
}
