package com.example.rideshare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rideshare.R;

import com.example.rideshare.adapter.RequestRideAdapter;
import com.example.rideshare.api.Rideshare_api;
import com.example.rideshare.model.riderequest;
import com.example.rideshare.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestFragment extends Fragment {

    private RecyclerView rev;


    public RequestFragment() {
        //Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request, container, false);


        rev= view.findViewById(R.id.recyclerhistory);
   ;

        loadRequestride();
        return  view;

    }

    private void loadRequestride() {

        Rideshare_api rideshareApi1 = url.getInstance().create(Rideshare_api.class);
        Call<List<riderequest>> requestCall = rideshareApi1.requestOffer(url.token);


        requestCall.enqueue(new Callback<List<riderequest>>() {
            @Override
            public void onResponse(Call<List<riderequest>> call, Response<List<riderequest>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                    List<riderequest> riderequestList = response.body();

                    RequestRideAdapter requestRideAdapter = new RequestRideAdapter(getContext(),riderequestList);
                    rev.setAdapter(requestRideAdapter);
                    rev.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onFailure(Call<List<riderequest>>  call, Throwable t) {

            }
        });
    }
}
