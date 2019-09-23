package com.thewhiteunicorn.trip_manager.ui.activities.manageStuffActivity.subcomponents;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thewhiteunicorn.trip_manager.R;
import com.thewhiteunicorn.trip_manager.model.StuffInSet;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class ManageStuffRealmReducerViewAdapter extends RealmRecyclerViewAdapter<StuffInSet, ManageStuffRealmReducerViewAdapter.StuffToTakeViewHolder> {


    public ManageStuffRealmReducerViewAdapter(@Nullable OrderedRealmCollection<StuffInSet> data) {
        super(data, true);
        setHasStableIds(true);
    }

    // listener

    @NonNull
    @Override
    public StuffToTakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_manage_stuff_item, parent, false);
        return new StuffToTakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StuffToTakeViewHolder stuffToTakeViewHolder, int position) {
        final StuffInSet stuffInSet = getItem(position);

        stuffToTakeViewHolder.bind(stuffInSet);
    }

    class StuffToTakeViewHolder extends RecyclerView.ViewHolder {
        final View mView;

        TextView mTitle;

        public StuffInSet data;

        StuffToTakeViewHolder(View view) {
            super(view);
            mView = view;

            mTitle = view.findViewById(R.id.manageStuff_item_title);
        }

        void bind(StuffInSet data){
            this.data = data;
            mTitle.setText(data.getStuffItem().getTitle());
        }
    }
}
