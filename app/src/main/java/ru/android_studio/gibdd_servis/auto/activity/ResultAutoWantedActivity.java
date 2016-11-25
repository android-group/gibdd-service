package ru.android_studio.gibdd_servis.auto.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.auto.model.wanted.ResultAutoWanted;
import ru.android_studio.gibdd_servis.auto.model.wanted.Wanted;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoWanted;


public class ResultAutoWantedActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.w_data_pu)
    TextView w_data_puTV;

    @BindView(R.id.w_god_vyp)
    TextView w_god_vypTV;

    @BindView(R.id.w_model)
    TextView w_modelTV;

    @BindView(R.id.w_reg_inic)
    TextView w_reg_inicTV;

    ParseResultAutoWanted parseAutoWanted = ParseResultAutoWanted.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_wanted_result);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        String resultText = extras.getString("result_text");

        ResultAutoWanted result = new ResultAutoWanted();
        parseAutoWanted.mapSuccessResult(resultText, result);

        for (Wanted wanted : result.getWantedList()) {
            w_data_puTV.setText(wanted.getW_data_pu());
            w_god_vypTV.setText(wanted.getW_god_vyp());
            w_modelTV.setText(wanted.getW_model());
            w_reg_inicTV.setText(wanted.getW_reg_inic());
        }

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
