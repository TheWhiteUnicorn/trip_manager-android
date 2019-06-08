package com.thewhiteunicorn.trip_manager.ui.activities.addStuffActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thewhiteunicorn.trip_manager.R;
import com.thewhiteunicorn.trip_manager.model.Location;
import com.thewhiteunicorn.trip_manager.model.StuffItem;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class AddStuffActivity extends AppCompatActivity {

    Realm mRealm;

    RealmResults<Location> locationsRealmResults;


    TextView mEditTextTitle;
    TextView mEditTextDescription;
    SearchableSpinner mSearchableSpinnerLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stuff);
        Toolbar toolbar = findViewById(R.id.addStuff_toolbar);
        setSupportActionBar(toolbar);


        mRealm = Realm.getDefaultInstance();
        locationsRealmResults = mRealm.where(Location.class).findAll();


        mEditTextTitle = findViewById(R.id.addStuff_editText_title);
        mEditTextDescription = findViewById(R.id.addStuff_editText_description);
        mSearchableSpinnerLocation = findViewById(R.id.addTrip_dispatchCard_searchableSpinner_location);


        onCreateLocationSpinner();

        FloatingActionButton fab = findViewById(R.id.addStuff_fab_save);
        fab.setOnClickListener(view -> {
            String title = mEditTextTitle.getText().toString();
            String description = mEditTextDescription.getText().toString();
            Location location = locationsRealmResults.get(mSearchableSpinnerLocation.getSelectedItemPosition());

            mRealm.beginTransaction();

            StuffItem stuffItem = mRealm.createObject(StuffItem.class);
            stuffItem.setTitle(title);
            stuffItem.setDescription(description);
            stuffItem.setLocation(location);

            mRealm.commitTransaction();

            finish();
        });
    }

    private void onCreateLocationSpinner(){
        mSearchableSpinnerLocation.setTitle("Select item's location");
        mSearchableSpinnerLocation.setPositiveButton("OK");

        List<Location> locationList = mRealm.copyFromRealm(locationsRealmResults);
        ArrayAdapter<Location> adapter = new ArrayAdapter<>(this, R.layout.activity_add_stuff_location_searchablespinner_item, locationList);
        mSearchableSpinnerLocation.setAdapter(adapter);
        /*mSearchableSpinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(AddStuffActivity.this, locationsRealmResults.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                Toast.makeText(AddStuffActivity.this, mSearchableSpinnerLocation.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/


    }

}
