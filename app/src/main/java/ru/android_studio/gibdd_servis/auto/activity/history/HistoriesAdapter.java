package ru.android_studio.gibdd_servis.auto.activity.history;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android_studio.gibdd_servis.R;
import ru.android_studio.gibdd_servis.auto.model.history.OwnershipPeriod;

public class HistoriesAdapter extends RecyclerView.Adapter<HistoriesAdapter.ViewHolder> {

    List<OwnershipPeriod> ownershipPeriods;

    public HistoriesAdapter(List<OwnershipPeriod> ownershipPeriods) {
        this.ownershipPeriods = ownershipPeriods;
    }

    @Override
    public HistoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ownership_period_recycler, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HistoriesAdapter.ViewHolder holder, int position) {
        OwnershipPeriod ownershipPeriod = this.ownershipPeriods.get(position);

        if(ownershipPeriod == null) {
            return;
        }

        holder.fromTV.setText(ownershipPeriod.getFrom());
        holder.idTV.setText(ownershipPeriod.getId());
        holder.toTV.setText(ownershipPeriod.getTo());
        holder.personTypeTV.setText(ownershipPeriod.getSimplePersonType().getText());
    }

    @Override
    public int getItemCount() {
        return ownershipPeriods.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //@BindView(R.id.from)
        TextView fromTV;

        //@BindView(R.id.to)
        TextView toTV;

        //@BindView(R.id.id)
        TextView idTV;

        //@BindView(R.id.person_type)
        TextView personTypeTV;

        public ViewHolder(View v) {
            super(v);
            //ButterKnife.bind(v);
            fromTV = (TextView)v.findViewById(R.id.from);
            toTV = (TextView)v.findViewById(R.id.to);
            idTV = (TextView)v.findViewById(R.id.id);
            personTypeTV = (TextView)v.findViewById(R.id.person_type);
        }
    }
}
