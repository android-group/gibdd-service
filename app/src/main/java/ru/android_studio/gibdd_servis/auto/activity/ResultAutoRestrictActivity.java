package ru.android_studio.gibdd_servis.auto.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.auto.model.restricted.ResultAutoRestricted;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoRestricted;


public class ResultAutoRestrictActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.result)
    TextView resultTextView;

    ParseResultAutoRestricted parseAutoRestricted = ParseResultAutoRestricted.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_restricted_result);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        String resultText = extras.getString("result_text");
        ResultAutoRestricted result = new ResultAutoRestricted();
        parseAutoRestricted.mapSuccessResult(resultText, result);
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
