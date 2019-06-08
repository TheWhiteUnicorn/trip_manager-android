package com.thewhiteunicorn.trip_manager.ui.fragments.stuffList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thewhiteunicorn.trip_manager.R;
import com.thewhiteunicorn.trip_manager.model.StuffItem;
import com.thewhiteunicorn.trip_manager.ui.activities.addStuffActivity.AddStuffActivity;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class StuffListFragment extends Fragment {

    private Realm mRealm;

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StuffListFragment() {
    }

    @SuppressWarnings("unused")
    public static StuffListFragment newInstance() {
        StuffListFragment fragment = new StuffListFragment();
        /*Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRealm = Realm.getDefaultInstance();

        mRealm.beginTransaction();


        /*StuffItem travel = mRealm.createObject(StuffItem.class);
        travel.setTitle("Phone");*/

        /*final RealmResults<StuffItem> results = mRealm.where(StuffItem.class).isNull("location").findAll();

        results.deleteAllFromRealm();*/

        mRealm.commitTransaction();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stuff_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.stuffList_list);
        Context context = recyclerView.getContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(layoutManager);

        OrderedRealmCollection<StuffItem> realmData = mRealm.where(StuffItem.class).findAll();
        recyclerView.setAdapter(new StuffListRealmRecyclerViewAdapter(realmData, mListener));

        FloatingActionButton fab = view.findViewById(R.id.stuffList_fab_add);
        fab.setOnClickListener(view1 -> {
            Intent intent = new Intent(context, AddStuffActivity.class);
            startActivity(intent);
        });

        return view;
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
        void onListFragmentInteraction(StuffItem item);
    }
}
