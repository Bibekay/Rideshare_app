package com.example.rideshare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.rideshare.R;
import com.example.rideshare.adapter.SectionPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class UserhomeFragment  extends Fragment {

    View myFragment;

    ViewPager viewPager;
    TabLayout tabLayout;


    public UserhomeFragment() {
        // Required empty public constructor
    }

    public static UserhomeFragment getInstance()    {
        return new UserhomeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_home_menu, container, false);

        viewPager = myFragment.findViewById(R.id.viewPager);
        tabLayout = myFragment.findViewById(R.id.tabLayout);

        return myFragment;
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
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new CurrentFragment(), "HOME");
        adapter.addFragment(new OfferRideFragment(), "OFFERS");
        adapter.addFragment(new RequestFragment(), "REQUESTS");

        viewPager.setAdapter(adapter);
    }
}
