package com.example.rideshare.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rideshare.R;
import com.example.rideshare.adapter.NotificationRideAdapter;
import com.example.rideshare.api.Rideshare_api;
import com.example.rideshare.model.ridepost;
import com.example.rideshare.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends Fragment {

    private RecyclerView rev;

    public NotificationFragment() {
            //Requires empty public constructor
        }


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            //inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_notification, container, false);


            rev= view.findViewById(R.id.recyclerhistory);

            loadOfferride();
            return  view;

        }

    private void loadOfferride() {


        Rideshare_api rideshareApi = url.getInstance().create(Rideshare_api.class);
        Call<List<ridepost>> ridepostCall = rideshareApi.getMyrides(url.token);
        ridepostCall.enqueue(new Callback<List<ridepost>>() {
            @Override
            public void onResponse(Call<List<ridepost>> call, Response<List<ridepost>> response) {
                if (response.body().size()== 0){

                }

                List<ridepost> ridepostList = response.body();


                NotificationRideAdapter no = new NotificationRideAdapter(getContext(), ridepostList);
                rev.setAdapter(no);
                rev.setLayoutManager(new LinearLayoutManager(getContext()));

            }


            @Override
            public void onFailure(Call<List<ridepost>> call, Throwable t) {

            }
        });

    }
}