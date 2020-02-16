package com.example.rideshare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rideshare.R;
import com.example.rideshare.activities.BikedetailsActivity;
import com.example.rideshare.activities.OfferrideActivity;
import com.example.rideshare.activities.PickrideActivity;


public class CurrentFragment  extends Fragment {
    public CurrentFragment() {
        //Requires empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current, container, false);


        //to view next activity offer ride activity layout
        Button btnOfferride = view.findViewById(R.id.btnOfferride);
        btnOfferride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openOfferride = new Intent(getActivity(), OfferrideActivity.class);
                startActivity(openOfferride);
            }
        });

        //to view next activity request ride activity layout
        Button btnPickride = view.findViewById(R.id.btnPickride);
        btnPickride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRequestride = new Intent(getActivity(), PickrideActivity.class);
                startActivity(openRequestride);
            }
        });



        return view;

    }
}
