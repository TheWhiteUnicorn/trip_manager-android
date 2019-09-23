package com.thewhiteunicorn.trip_manager.ui.activities.manageStuffActivity.subcomponents;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.thewhiteunicorn.trip_manager.R;
import com.thewhiteunicorn.trip_manager.model.StuffInSet;
import com.thewhiteunicorn.trip_manager.model.StuffItem;
import com.thewhiteunicorn.trip_manager.model.StuffSet;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


/**
 * A placeholder fragment containing a simple view.
 */
public class ManageStuffFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    //private PageViewModel pageViewModel;

    StuffSet stuffSet;
    RealmResults<StuffInSet> realmData;


    public static ManageStuffFragment newInstance(int index) {
        ManageStuffFragment fragment = new ManageStuffFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm mRealm = Realm.getDefaultInstance();

        long stuffSetId = 0;
        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null) {
            stuffSetId = extras.getLong("STUFF_SET_ID");
        }

        stuffSet = mRealm
                .where(StuffSet.class)
                .equalTo("id", stuffSetId)
                .findFirst();

        RealmList<StuffInSet> oldStuffList = stuffSet.getStuffItems();
        RealmList<StuffInSet> newPrefilledStuffList = new RealmList<>();

        RealmResults<StuffItem> allStuff = mRealm.where(StuffItem.class).findAll();

        /*mRealm.beginTransaction();
        for (StuffItem item:
             allStuff) {
            StuffInSet newItem = mRealm.createObject(StuffInSet.class);
            newItem.setStuffItem(item);
            newItem.setQty(0);
            newPrefilledStuffList.add(newItem);
        }

        /*if (oldStuffList != null) { TODO: get done
            for (StuffInSet item:
                 oldStuffList) {

            }
        }

        stuffSet.setStuffItems(newPrefilledStuffList);

        mRealm.commitTransaction();*/

        int tabIndex = 1;
        if (getArguments() != null) {
            tabIndex = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        switch (tabIndex) {
            case 1:
                realmData = stuffSet.getStuffItems().where().findAll();
            case 2:
                realmData = stuffSet.getStuffItems().where().notEqualTo("qty", 0).findAll();
            case 3:
                realmData = stuffSet.getStuffItems().where().equalTo("qty", 0).findAll();
        }

        /*pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);*/
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_manage_stuff, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.manageStuff_list);
        Context context = recyclerView.getContext();

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        ManageStuffRealmReducerViewAdapter adapter = new ManageStuffRealmReducerViewAdapter(realmData);
        recyclerView.setAdapter(adapter);


        /*final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}