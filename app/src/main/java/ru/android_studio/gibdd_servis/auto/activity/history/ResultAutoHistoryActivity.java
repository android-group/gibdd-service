package ru.android_studio.gibdd_servis.auto.activity.history;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.auto.model.history.ResultAutoHistory;
import ru.android_studio.gibdd_servis.auto.model.history.Vehicle;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoHistory;


public class ResultAutoHistoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.ownership_period_recycler_view)
    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;

    RecyclerView.LayoutManager layoutManager;

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

    @BindView(R.id.engine_number)
    TextView engineNumberTextView;

    @BindView(R.id.engine_volume)
    TextView engineVolumeTextView;

    @BindView(R.id.power_hp)
    TextView powerHpTextView;

    ParseResultAutoHistory parseAutoHistory = ParseResultAutoHistory.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_history_result);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        String resultText = extras.getString("result_text");
        ResultAutoHistory result = new ResultAutoHistory();
        parseAutoHistory.mapSuccessResult(resultText, result);
        fillVehicle(result.getVehicle());

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new HistoriesAdapter(result.getOwnershipPeriodList());
        recyclerView.setAdapter(adapter);

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

    private void fillVehicle(Vehicle vehicle) {
        typeTextView.setText(vehicle.getType().getText());
        bodyNumberTextView.setText(vehicle.getBodyNumber());
        categoryTextView.setText(vehicle.getCategory());
        colorTextView.setText(vehicle.getColor());
        engineNumberTextView.setText(vehicle.getEngineNumber());
        bodyNumberTextView.setText(vehicle.getBodyNumber());
        engineVolumeTextView.setText(vehicle.getEngineVolume());
        modelTextView.setText(vehicle.getModel());
        powerHpTextView.setText(vehicle.getPowerHp());
        powerKwtTextView.setText(vehicle.getPowerKwt());
        vinTextView.setText(vehicle.getVin());
        yearTextView.setText(vehicle.getYear());
    }
}
