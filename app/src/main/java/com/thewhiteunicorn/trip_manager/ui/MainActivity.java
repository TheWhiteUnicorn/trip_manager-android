package com.thewhiteunicorn.trip_manager.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.thewhiteunicorn.trip_manager.R;
import com.thewhiteunicorn.trip_manager.ui.fragments.tripList.TripListFragment;

public class MainActivity extends AppCompatActivity implements TripListFragment.OnListFragmentInteractionListener {
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
                    fragment = TripListFragment.newInstance();
                    break;
                case R.id.navigation_notifications:
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
}
