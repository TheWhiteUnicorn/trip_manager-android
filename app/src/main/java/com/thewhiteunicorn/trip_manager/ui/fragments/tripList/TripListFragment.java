package com.thewhiteunicorn.trip_manager.ui.fragments.tripList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;
import com.thewhiteunicorn.trip_manager.model.Travel;
import com.thewhiteunicorn.trip_manager.R;
import com.thewhiteunicorn.trip_manager.ui.activities.addLocationActivity.AddLocationActivity;
import com.thewhiteunicorn.trip_manager.ui.activities.addTripActivity.AddTripActivity;

import io.realm.Realm;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TripListFragment extends Fragment {

    /*// TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;*/
    private OnListFragmentInteractionListener mListener;

    private Realm mRealm;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TripListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TripListFragment newInstance() {
        TripListFragment fragment = new TripListFragment();
        //Bundle args = new Bundle();
        //args.putInt(ARG_COLUMN_COUNT, columnCount);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }*/

        mRealm = Realm.getDefaultInstance();
        //final RealmResults<Travel> results = mRealm.where(Travel.class).equalTo("title", "Test1").findAll();


        // mRealm.beginTransaction();


        /*Travel travel = mRealm.createObject(Travel.class);
        travel.setTitle("Vocation 2019");
        travel.setDispatchDate(new Date(2019, 4, 10));
        travel.setArriveDate(new Date(2019, 4, 20));*/

        // results.deleteAllFromRealm();


        // mRealm.commitTransaction();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_triplist, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.triplist_list);
        Context context = recyclerView.getContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, layoutManager.getOrientation());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(new TripListRealmRecyclerViewAdapter(mRealm.where(Travel.class).findAll()));

        onCreateViewFAB(view, context);

        return view;
    }

    private void onCreateViewFAB (View view, Context context) {
        SpeedDialView speedDialView = view.findViewById(R.id.floatingActionButton);
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.trips_fab_action_action_create_trip, R.drawable.bus)
                        .setFabImageTintColor(Color.WHITE)
                        .setLabel(getString(R.string.trips_fab_action_trip_text))
                        .setLabelBackgroundColor(Color.WHITE)
                        .setLabelClickable(false)
                        .create()
        );
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.trips_fab_action_create_trip_group, R.drawable.folder_multiple)
                        .setFabImageTintColor(Color.WHITE)
                        .setLabel(getString(R.string.trips_fab_action_group_text))
                        .setLabelBackgroundColor(Color.WHITE)
                        .setLabelClickable(false)
                        .create()
        );

        speedDialView.setOnActionSelectedListener(speedDialActionItem -> {
            switch (speedDialActionItem.getId()) {
                case R.id.trips_fab_action_action_create_trip:
                    Intent intent = new Intent(context, AddTripActivity.class);
                    startActivity(intent);
                    return false; // true to keep the Speed Dial open
                case R.id.trips_fab_action_create_trip_group:
                    Toast.makeText(context, "Creating trip group", Toast.LENGTH_SHORT).show();
                    return false; // true to keep the Speed Dial open
                default:
                    return false;
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name

    }
}
