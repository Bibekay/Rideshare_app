package com.example.rideshare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.rideshare.R;
import com.example.rideshare.adapter.NotifySectionPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class NotifyFragment extends Fragment {

    View myfragment;
    ViewPager viewPager;
    TabLayout tabLayout;

    public NotifyFragment() {
        //Required empty public constructor
    }

    public static NotifyFragment getInstance() {
        return new NotifyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the layout for this frament
        myfragment = inflater.inflate(R.layout.fragment_notify, container, false);


        viewPager = myfragment.findViewById(R.id.viewPager);
        tabLayout = myfragment.findViewById(R.id.tabLayout);

        return myfragment;
    }

    //Call onActivity Create method


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setUpViewPager(ViewPager viewPager) {
        NotifySectionPagerAdapter adapter = new NotifySectionPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new MypostFragment(), "My Posts");
        adapter.addFragment(new NotificationFragment(), "Notification");

        viewPager.setAdapter(adapter);
    }

}

