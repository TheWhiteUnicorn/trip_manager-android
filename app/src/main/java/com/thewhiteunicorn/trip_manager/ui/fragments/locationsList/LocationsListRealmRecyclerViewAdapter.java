package com.thewhiteunicorn.trip_manager.ui.fragments.locationsList;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thewhiteunicorn.trip_manager.R;
import com.thewhiteunicorn.trip_manager.model.Location;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class LocationsListRealmRecyclerViewAdapter extends RealmRecyclerViewAdapter<Location, LocationsListRealmRecyclerViewAdapter.LocationListViewHolder> {

    private final LocationsListFragment.OnListFragmentInteractionListener mListener;

    LocationsListRealmRecyclerViewAdapter(@Nullable OrderedRealmCollection<Location> data, LocationsListFragment.OnListFragmentInteractionListener listener) {
        super(data, true);
        setHasStableIds(true);
        mListener = listener;
    }

    @Override
    public LocationListViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_locations_item, parent, false);
        return new LocationListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LocationListViewHolder locationListHolder, int position) {
        final Location location = getItem(position);

        locationListHolder.data = location;
        locationListHolder.mTitle.setText(location.getTitle());
        locationListHolder.mDescription.setText(location.getDescription());
        locationListHolder.mAddress.setText(location.getAddress());

        locationListHolder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(locationListHolder.data);
            }
        });
    }

    /*@Override
    public long getItemId(int index) {
        return getItem(index).getId();
    }*/

    class LocationListViewHolder extends RecyclerView.ViewHolder {
        final View mView;

        final TextView mTitle;
        final TextView mDescription;
        final TextView mAddress;

        Location data;

        LocationListViewHolder(View view) {
            super(view);
            mView = view;

            mTitle = view.findViewById(R.id.locationsList_item_title);
            mDescription = view.findViewById(R.id.locationsList_item_description);
            mAddress = view.findViewById(R.id.locationsList_item_address);
        }
    }
}
