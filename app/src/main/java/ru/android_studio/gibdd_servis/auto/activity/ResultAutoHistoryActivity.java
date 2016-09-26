package ru.android_studio.gibdd_servis.auto.activity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.ActivityWithMenu;
import ru.android_studio.gibdd_servis.ItemFragment;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.auto.model.history.OwnershipPeriod;
import ru.android_studio.gibdd_servis.auto.model.history.ResultAutoHistory;
import ru.android_studio.gibdd_servis.auto.model.history.Vehicle;
import ru.android_studio.gibdd_servis.auto.parser.ParseResultAutoHistory;


public class ResultAutoHistoryActivity extends ActivityWithMenu implements ItemFragment.OnListFragmentInteractionListener {

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
        fillOwnershipPeriodList(result.getOwnershipPeriodList());

        addToolbarByIconId(R.mipmap.ic_auto);
    }

    // Периоды владения транспортным средством
    private void fillOwnershipPeriodList(List<OwnershipPeriod> ownershipPeriodList) {

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

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_car_btn;
    }

    @Override
    public void onListFragmentInteraction(OwnershipPeriod item) {

    }
}
