package com.thewhiteunicorn.trip_manager.ui.activities.addTripActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thewhiteunicorn.trip_manager.R;
import com.thewhiteunicorn.trip_manager.model.Location;
import com.thewhiteunicorn.trip_manager.model.StuffSet;
import com.thewhiteunicorn.trip_manager.model.Travel;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class AddTripActivity extends AppCompatActivity {

    Realm mRealm;

    RealmResults<Location> locationsRealmResults;

    TextView mEditTextTitle;
    TextView mEditTextNote;

    SearchableSpinner mSearchableSpinnerDispatchLocation;
    TextView mEditTextDispatchDate;
    TextView mEditTextDispatchTime;

    SearchableSpinner mSearchableSpinnerArriveLocation;
    TextView mEditTextArriveDate;
    TextView mEditTextArriveTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRealm = Realm.getDefaultInstance();
        locationsRealmResults = mRealm.where(Location.class).findAll();

        mEditTextTitle = findViewById(R.id.addTrip_editText_title);
        mEditTextNote = findViewById(R.id.addTrip_editText_note);

        mSearchableSpinnerDispatchLocation = findViewById(R.id.addTrip_dispatchCard_searchableSpinner_location);
        mEditTextDispatchDate = findViewById(R.id.addTrip_dispatchCard_date);
        mEditTextDispatchTime = findViewById(R.id.addTrip_dispatchCard_time);

        mSearchableSpinnerArriveLocation = findViewById(R.id.addTrip_arriveCard_searchableSpinner_location);
        mEditTextArriveDate = findViewById(R.id.addTrip_arriveCard_date);
        mEditTextArriveTime = findViewById(R.id.addTrip_arriveCard_time);

        onCreateLocationSpinners();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy'T'HH:mm");


            String title = mEditTextTitle.getText().toString();
            String note = mEditTextNote.getText().toString();

            Location locationDispatch = locationsRealmResults.get(mSearchableSpinnerDispatchLocation.getSelectedItemPosition());
            String dateDispatch = mEditTextDispatchDate.getText().toString();
            String timeDispatch = mEditTextDispatchTime.getText().toString();

            Location locationArrive = locationsRealmResults.get(mSearchableSpinnerArriveLocation.getSelectedItemPosition());
            String dateArrive = mEditTextArriveDate.getText().toString();
            String timeArrive = mEditTextArriveTime.getText().toString();

            Date dateTimeDispatch = null;
            Date dateTimeArrive = null;

            try {
                dateTimeDispatch = format.parse(dateDispatch + "T" + timeDispatch);
                //Date dateTimeArrive = format.parse(dateArrive + "T" + timeArrive);

                /*Snackbar.make(view, title +
                                note +
                                locationArrive.getTitle() +
                                locationDispatch.getTitle() +
                                dateTimeDispatch
                        , Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            } catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                dateTimeArrive = format.parse(dateArrive + "T" + timeArrive);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            mRealm.beginTransaction();

            Travel travel = mRealm.createObject(Travel.class);
            travel.setTitle(title);
            travel.setNote(note);
            travel.setStartPoint(locationDispatch);
            travel.setDestinationPoint(locationArrive);
            travel.setDispatchDate(dateTimeDispatch);
            travel.setArriveDate(dateTimeArrive);

            StuffSet stuffSet = mRealm.createObject(StuffSet.class);
            travel.setStuffSet(stuffSet);

            mRealm.commitTransaction();


        });
    }

    private void onCreateLocationSpinners(){
        List<Location> locationList = mRealm.copyFromRealm(locationsRealmResults);

        mSearchableSpinnerDispatchLocation.setTitle("Select dispatch location");
        mSearchableSpinnerDispatchLocation.setPositiveButton("OK");

        ArrayAdapter<Location> adapterDispatch = new ArrayAdapter<>(this, R.layout.activity_add_stuff_location_searchablespinner_item, locationList);
        mSearchableSpinnerDispatchLocation.setAdapter(adapterDispatch);

        mSearchableSpinnerArriveLocation.setTitle("Select arrive location");
        mSearchableSpinnerArriveLocation.setPositiveButton("OK");

        ArrayAdapter<Location> adapterArrive = new ArrayAdapter<>(this, R.layout.activity_add_stuff_location_searchablespinner_item, locationList);
        mSearchableSpinnerArriveLocation.setAdapter(adapterArrive);
    }

}
