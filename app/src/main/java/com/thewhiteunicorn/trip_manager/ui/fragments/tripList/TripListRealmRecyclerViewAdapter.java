package com.thewhiteunicorn.trip_manager.ui.fragments.tripList;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thewhiteunicorn.trip_manager.model.Travel;
import com.thewhiteunicorn.trip_manager.R;

import java.text.SimpleDateFormat;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class TripListRealmRecyclerViewAdapter extends RealmRecyclerViewAdapter<Travel, TripListRealmRecyclerViewAdapter.TripListViewHolder> {

    public TripListRealmRecyclerViewAdapter(@Nullable OrderedRealmCollection<Travel> data) {
        super(data, true);
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public TripListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View travelView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_triplist_item, parent, false);
        return new TripListViewHolder(travelView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripListViewHolder tripListViewHolder, int i) {
        final Travel travel = getItem(i);
        tripListViewHolder.data = travel;

        tripListViewHolder.title.setText(travel.getTitle());


        String datesInterval = (new StringBuilder()
                .append(SimpleDateFormat.getDateInstance().format(travel.getDispatchDate()))
                .append(" - ")
                .append(SimpleDateFormat.getDateInstance().format(travel.getArriveDate()))
                .toString()
        );
        tripListViewHolder.dates.setText(datesInterval);
    }

    @Override
    public long getItemId(int index) {
        return getItem(index).getId();
    }

    class TripListViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView dates;

        public Travel data;

        TripListViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tripList_item_title);
            dates = view.findViewById(R.id.tripList_item_dates);
        }
    }
}
