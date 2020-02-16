package com.example.rideshare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rideshare.R;
import com.example.rideshare.api.Rideshare_api;
import com.example.rideshare.model.ridepost;
import com.example.rideshare.url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MypostFragment extends Fragment {

    String id;

    TextView tvPickuppoint, tvDestination, tvJourneydate, tvTime,tvPaidornot,tvCost;
    Button btnUpdatepost, btnDelete;

    public MypostFragment() {
        //Requires empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mypost, container, false);


        tvPickuppoint = view.findViewById(R.id.etRequestpickpoint);
        tvDestination = view.findViewById(R.id.etRequestDestinationpoint);
        tvJourneydate = view.findViewById(R.id.etRequestdate);
        tvTime = view.findViewById(R.id.etRequesttime);
        tvPaidornot = view.findViewById(R.id.etRequestridetype);
        tvCost = view.findViewById(R.id.etRequestcost);
        btnUpdatepost = view.findViewById(R.id.btnUpdatepost);
        btnDelete = view.findViewById(R.id.btnDelete);


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletepost();
                tvPickuppoint.setText("");
                tvDestination.setText("");
                tvJourneydate.setText("");
                tvTime.setText("");
                tvPaidornot.setText("");
                tvCost.setText("");
            }
        });

        btnUpdatepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePost();
            }
        });

        myPost();
        return  view;


    }

    private void deletepost() {

        Rideshare_api rideshareApi = url.getInstance().create(Rideshare_api.class);
        Call<ridepost> voidCall = rideshareApi.deletePost(url.token, id);

        voidCall.enqueue(new Callback<ridepost>() {
            @Override
            public void onResponse(Call<ridepost> call, Response<ridepost> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(), "Deleted !!!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ridepost> call, Throwable t) {
                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }

    private void updatePost() {

        ridepost ridepost = new ridepost(
                tvPickuppoint.getText().toString(),
                tvDestination.getText().toString(),
                tvJourneydate.getText().toString(),
                tvTime.getText().toString(),
                tvPaidornot.getText().toString(),
                tvCost.getText().toString()
        );
        Rideshare_api rideshareApi = url.getInstance().create(Rideshare_api.class);
        Call<ridepost> voidCall = rideshareApi.updateMyrides(url.token, id,ridepost);
        voidCall.enqueue(new Callback<ridepost>() {
            @Override
            public void onResponse(Call<ridepost> call, Response<ridepost> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(), "Updated !!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ridepost> call, Throwable t) {
                Toast.makeText(getActivity(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void myPost() {

        Rideshare_api rideshareApi = url.getInstance().create(Rideshare_api.class);
        Call<List<ridepost>> notifiyCall = rideshareApi.myPost(url.token);

        notifiyCall.enqueue(new Callback<List<ridepost>>() {
            @Override
            public void onResponse(Call<List<ridepost>> call, Response<List<ridepost>> response) {
                if (response.body().size()== 0){


                }
                else{
                    id=(response.body().get(0).get_id());
                    tvPickuppoint.setText(response.body().get(0).getPickuppoint());
                    tvDestination.setText(response.body().get(0).getDestination());
                    tvJourneydate.setText(response.body().get(0).getJourneydate());
                    tvTime.setText(response.body().get(0).getTime());
                    tvPaidornot.setText(response.body().get(0).getPaidornot());
                    tvCost.setText(response.body().get(0).getCost());
                }

            }

            @Override
            public void onFailure(Call<List<ridepost>> call, Throwable t) {

            }
        });



    }
}