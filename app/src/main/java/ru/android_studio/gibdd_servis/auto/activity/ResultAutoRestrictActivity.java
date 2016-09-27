package ru.android_studio.gibdd_servis.auto.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.auto.model.restricted.RestrictedItem;
import ru.android_studio.gibdd_servis.auto.model.restricted.ResultAutoRestricted;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoRestricted;


public class ResultAutoRestrictActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tsmodel)
    TextView tsmodelTV;

    @BindView(R.id.tsyear)
    TextView tsyearTV;

    @BindView(R.id.dateogr)
    TextView dateogrTV;

    @BindView(R.id.regname)
    TextView regnameTV;

    @BindView(R.id.divtype)
    TextView divtypeTV;

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

        List<RestrictedItem> restrictedItem = result.getRestrictedItem();
        for (RestrictedItem item : restrictedItem) {
            tsmodelTV.setText(item.getTsmodel());
            tsyearTV.setText(item.getTsyear());
            dateogrTV.setText(item.getDateogr());
            regnameTV.setText(item.getRegname());
            divtypeTV.setText(item.getDivtype().getText());
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
