package com.thewhiteunicorn.trip_manager.ui.activities.addLocationActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.thewhiteunicorn.trip_manager.R;
import com.thewhiteunicorn.trip_manager.model.Location;

import io.realm.Realm;

public class AddLocationActivity extends AppCompatActivity {

    Realm mRealm;
    TextView mEditTextTitle;
    TextView mEditTextDescription;
    TextView mEditTextAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        mEditTextTitle = findViewById(R.id.addLocation_editText_title);
        mEditTextDescription = findViewById(R.id.addLocation_editText_Description);
        mEditTextAddress = findViewById(R.id.addLocation_editText_Address);


        mRealm = Realm.getDefaultInstance();

        FloatingActionButton fab = findViewById(R.id.addLocation_fab_save);
        fab.setOnClickListener(view -> {
            String title = mEditTextTitle.getText().toString();
            String description = mEditTextDescription.getText().toString();
            String address = mEditTextAddress.getText().toString();

            mRealm.beginTransaction();

            Location location = mRealm.createObject(Location.class);
            location.setTitle(title);
            location.setDescription(description);
            location.setAddress(address);

            mRealm.commitTransaction();

            finish();
        });
    }

}
