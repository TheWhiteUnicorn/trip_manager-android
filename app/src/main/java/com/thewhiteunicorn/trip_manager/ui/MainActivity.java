package com.thewhiteunicorn.trip_manager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.Toast;

import com.thewhiteunicorn.trip_manager.R;
import com.thewhiteunicorn.trip_manager.model.Location;
import com.thewhiteunicorn.trip_manager.model.StuffItem;
import com.thewhiteunicorn.trip_manager.model.Travel;
import com.thewhiteunicorn.trip_manager.ui.activities.manageStuffActivity.ManageStuff;
import com.thewhiteunicorn.trip_manager.ui.fragments.locationsList.LocationsListFragment;
import com.thewhiteunicorn.trip_manager.ui.fragments.stuffList.StuffListFragment;
import com.thewhiteunicorn.trip_manager.ui.fragments.tripList.TripListFragment;

public class MainActivity extends AppCompatActivity implements
        TripListFragment.OnListFragmentInteractionListener,
        StuffListFragment.OnListFragmentInteractionListener,
        LocationsListFragment.OnListFragmentInteractionListener
{
    private Fragment fragment;
    private FragmentManager fragmentManager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = TripListFragment.newInstance();
                    break;
                case R.id.navigation_dashboard:
                    fragment = StuffListFragment.newInstance();
                    break;
                case R.id.navigation_notifications:
                    fragment = LocationsListFragment.newInstance();
                    break;
            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_container, fragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager = getSupportFragmentManager();

        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragment = new TripListFragment();
        transaction.add(R.id.main_container, fragment).commit();
    }

    @Override
    public void onListFragmentInteraction(StuffItem item){
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListFragmentInteraction(Location item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListFragmentInteraction(Travel item){
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, ManageStuff.class);
        intent.putExtra("STUFF_SET_ID", item.getStuffSet().getId());
        startActivity(intent);
    }
}
