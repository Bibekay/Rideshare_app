package com.example.rideshare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.rideshare.R;
import com.example.rideshare.fragment.NotifyFragment;
import com.example.rideshare.fragment.ProfileFragment;
import com.example.rideshare.fragment.SearchFragment;
import com.example.rideshare.fragment.UserhomeFragment;
import com.example.rideshare.helper.BottomNavigationHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserDashboardActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdashboard);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId())   {
                    case R.id.action_home:
                        selectedFragment = UserhomeFragment.getInstance();
                        break;

                    case R.id.action_search:
                        selectedFragment = SearchFragment.getInstance();
                        break;

                    case R.id.action_notify:
                        selectedFragment = NotifyFragment.getInstance();
                        break;

                    case R.id.action_profile:
                        selectedFragment = ProfileFragment.getInstance();
                        break;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame,selectedFragment);
                transaction.commit();
                return true;
            }
        });
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame,UserhomeFragment.getInstance());
        transaction.commit();
    }

}

