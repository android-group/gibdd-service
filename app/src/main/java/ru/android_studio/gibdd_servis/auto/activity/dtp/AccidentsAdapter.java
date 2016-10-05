package ru.android_studio.gibdd_servis.auto.activity.dtp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.auto.model.dtp.Accidents;

public class AccidentsAdapter extends RecyclerView.Adapter<AccidentsAdapter.ViewHolder> {

    List<Accidents> accidents;

    public AccidentsAdapter(List<Accidents> accidents) {
        this.accidents = accidents;
    }

    @Override
    public AccidentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.accidents, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AccidentsAdapter.ViewHolder holder, int position) {
        Accidents accidents = this.accidents.get(position);

        if(accidents == null) {
            return;
        }

        holder.vehicleMarkTV.setText(accidents.getVehicleMark());
        holder.accidentDateTimeTV.setText(accidents.getAccidentDateTime());
        holder.accidentTypeTV.setText(accidents.getAccidentType());
        holder.regionNameTV.setText(accidents.getRegionName());
    }

    @Override
    public int getItemCount() {
        return accidents.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vehicle_mark)
        TextView vehicleMarkTV;

        @BindView(R.id.accident_dateTime)
        TextView accidentDateTimeTV;

        @BindView(R.id.accident_type)
        TextView accidentTypeTV;

        @BindView(R.id.region_name)
        TextView regionNameTV;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(v);
        }
    }
}
