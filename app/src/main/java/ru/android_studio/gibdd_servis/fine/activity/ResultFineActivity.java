package ru.android_studio.gibdd_servis.fine.activity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.ActivityWithToolbarAndOCR;
import ru.android_studio.gibdd_servis.R;


public class ResultFineActivity extends ActivityWithToolbarAndOCR {

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
}
