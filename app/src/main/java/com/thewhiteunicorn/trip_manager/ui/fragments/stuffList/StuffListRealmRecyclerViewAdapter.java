package com.thewhiteunicorn.trip_manager.ui.fragments.stuffList;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thewhiteunicorn.trip_manager.R;
import com.thewhiteunicorn.trip_manager.model.StuffItem;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class StuffListRealmRecyclerViewAdapter extends RealmRecyclerViewAdapter<StuffItem, StuffListRealmRecyclerViewAdapter.StuffListViewHolder> {

    private final StuffListFragment.OnListFragmentInteractionListener mListener;

    StuffListRealmRecyclerViewAdapter(@Nullable OrderedRealmCollection<StuffItem> data, StuffListFragment.OnListFragmentInteractionListener listener) {
        super(data, true);
        setHasStableIds(true);
        mListener = listener;
    }

    @Override
    public StuffListViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_stuff_item, parent, false);
        return new StuffListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StuffListViewHolder stuffListHolder, int position) {
        final StuffItem stuffItem = getItem(position);

        stuffListHolder.data = stuffItem;
        stuffListHolder.mTitle.setText(stuffItem.getTitle());
        stuffListHolder.mLocation.setText(stuffItem.getLocation().getTitle());
        stuffListHolder.mDescription.setText(stuffItem.getDescription());

        stuffListHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(stuffListHolder.data);
                }
            }
        });
    }

    /*@Override
    public long getItemId(int index) {
        return getItem(index).getId();
    }*/

    class StuffListViewHolder extends RecyclerView.ViewHolder {
        final View mView;

        final TextView mTitle;
        final TextView mLocation;
        final TextView mDescription;

        StuffItem data;

        StuffListViewHolder(View view) {
            super(view);
            mView = view;

            mTitle = view.findViewById(R.id.stuffList_item_title);
            mLocation = view.findViewById(R.id.stuffList_item_location);
            mDescription = view.findViewById(R.id.stuffList_item_description);
        }
    }
}
