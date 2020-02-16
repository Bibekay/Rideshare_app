package com.example.rideshare.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rideshare.R;
import com.example.rideshare.api.Rideshare_api;
import com.example.rideshare.model.ridepost;
import com.example.rideshare.url.url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferrideActivity extends AppCompatActivity {

    EditText etPickpoint, etDestinationpoint, etDate, etTime, etRidetype, etCost;
    Button Offerride;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offerride);

        etPickpoint = findViewById(R.id.etPickpoint);
        etDestinationpoint = findViewById(R.id.etDestinationpoint);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        etRidetype = findViewById(R.id.etRidetype);
        etCost = findViewById(R.id.etCost);
        Offerride = findViewById(R.id.btnPostride);



        Offerride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){

                    addOffer();
                    etPickpoint.setText("");
                    etDestinationpoint.setText("");
                    etDate.setText("");
                    etTime.setText("");
                    etRidetype.setText("");
                    etCost.setText("");

                }



            }
        });
    }

    private boolean validate() {
    boolean status = true;
        //Validation for offerride
        final String pickuppoint=etPickpoint.getText().toString();
        if(pickuppoint.length()==0 && !pickuppoint.matches("[a-zA-Z ]+"))

        {
            etPickpoint.requestFocus();
            etPickpoint.setError("Pickup point is empty!!!");
            status = false;
        }

        final String destination=etDestinationpoint.getText().toString();
        if(destination.length()==0 && !destination.matches("[a-zA-Z ]+"))

        {
            etDestinationpoint.requestFocus();
            etDestinationpoint.setError("Invalid input!!!");
            status = false;
        }

        final String date=etDate.getText().toString();
        if(date.length()==0 )

        {
            etDate.requestFocus();
            etDate.setError("Invalid input!!!");
            status = false;
        }

        final String type=etRidetype.getText().toString();
        if(type.length()==0 && !type.matches("[a-zA-Z ]+"))

        {
            etRidetype.requestFocus();
            etRidetype.setError("Invalid input!!!");
            status = false;
        }


        final String time=etTime.getText().toString();
        if(time.length()==0 )

        {
            etTime.requestFocus();
            etTime.setError("Invalid input!!!");
            status = false;
        }

        return status;
    }

    private void addOffer() {



        String pickuppoint = etPickpoint.getText().toString();
        String destination =   etDestinationpoint.getText().toString();
        String journeydate = etDate.getText().toString();
        String time =  etTime.getText().toString();
        String paidornot = etRidetype.getText().toString();
        String cost = etCost.getText().toString();

        ridepost ridepost = new ridepost(pickuppoint, destination, journeydate, time,paidornot, cost);
        Rideshare_api rideshareApi = url.getInstance().create(Rideshare_api.class);
        Call<ridepost>voidCall = rideshareApi.rideposts(url.token,ridepost);
        voidCall.enqueue(new Callback<ridepost>() {
            @Override
            public void onResponse(Call<ridepost> call, Response<ridepost> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(OfferrideActivity.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(OfferrideActivity.this, "Post successful !!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ridepost> call, Throwable t) {
                Toast.makeText(OfferrideActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
