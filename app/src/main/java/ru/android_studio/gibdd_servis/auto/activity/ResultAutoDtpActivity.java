package ru.android_studio.gibdd_servis.auto.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.auto.model.dtp.ResultAutoDtp;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoDtp;


public class ResultAutoDtpActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.result)
    TextView resultTextView;

    ParseResultAutoDtp parseAutoDtp = ParseResultAutoDtp.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_dtp_result);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        String resultText = extras.getString("result_text");
        ResultAutoDtp result = new ResultAutoDtp();
        parseAutoDtp.mapSuccessResult(resultText, result);
        resultTextView.setText(result.toString());
        addToolbarByIcon();
    }

    private void addToolbarByIcon() {
        toolbar.setNavigationIcon(R.mipmap.ic_action_back);
        toolbar.setLogo(R.mipmap.ic_auto);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowTitleEnabled(true);
    }
}
