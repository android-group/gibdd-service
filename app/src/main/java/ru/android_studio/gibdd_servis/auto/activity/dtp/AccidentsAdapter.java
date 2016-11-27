package ru.android_studio.gibdd_servis.auto.activity.dtp;

import android.support.v7.widget.RecyclerView;
import android.text.AlteredCharSequence;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.auto.model.dtp.Accidents;

class AccidentsAdapter extends RecyclerView.Adapter<AccidentsAdapter.ViewHolder> {

    private static final int HEADER_TYPE = 0;
    private static final int ROW_TYPE = 1;

    private List<Accidents> accidents;

    AccidentsAdapter(List<Accidents> accidents) {
        this.accidents = accidents;
    }

    @Override
    public AccidentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accidents, parent, false);
            return new ViewHolder(view, viewType);

        } else if (viewType == ROW_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accidents, parent, false);
            return new ViewHolder(view, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(AccidentsAdapter.ViewHolder holder, int position) {
        Accidents accidents = this.accidents.get(position);

        if(accidents == null) {
            return;
        }

        String number = " " + (position + 1);
        holder.titleAccidentInfo.append(number);
        holder.vehicleMarkTV.setText(accidents.getVehicleMark());
        holder.vehicleYear.setText(accidents.getVehicleYear());
        holder.accidentDateTimeTV.setText(accidents.getAccidentDateTime());
        holder.accidentTypeTV.setText(accidents.getAccidentType());
        holder.regionNameTV.setText(accidents.getRegionName());
    }

    @Override
    public int getItemCount() {
        return accidents.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_accident_info)
        TextView titleAccidentInfo;

        @BindView(R.id.vehicle_mark)
        TextView vehicleMarkTV;

        @BindView(R.id.vehicle_year)
        TextView vehicleYear;

        @BindView(R.id.accident_dateTime)
        TextView accidentDateTimeTV;

        @BindView(R.id.accident_type)
        TextView accidentTypeTV;

        @BindView(R.id.region_name)
        TextView regionNameTV;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
