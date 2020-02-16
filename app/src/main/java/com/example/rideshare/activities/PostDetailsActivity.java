package com.example.rideshare.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rideshare.R;
import com.example.rideshare.api.Rideshare_api;
import com.example.rideshare.model.ridepost;
import com.example.rideshare.url.url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostDetailsActivity extends AppCompatActivity {
    TextView tvPickuppoint, tvDestination, tvJourneydate, tvTime,tvPaidornot,tvCost;
    Button btnConform;
    String id;


    @Override
    protected  void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_post_details);
        tvPickuppoint = findViewById(R.id.tvPickuppoint);
        tvDestination = findViewById(R.id.tvDestination);
        tvJourneydate = findViewById(R.id.tvJourneydate);
        tvTime = findViewById(R.id.tvTime);
        tvPaidornot = findViewById(R.id.tvPaidornot);
        tvCost = findViewById(R.id.tvCost);
        btnConform = findViewById(R.id.btnConform);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id= bundle.getString("id");
            tvPickuppoint.setText(bundle.getString("pickuppoint"));
            tvDestination.setText(bundle.getString("destination"));
            tvJourneydate.setText(bundle.getString("journeydate"));
            tvTime.setText(bundle.getString("time"));
            tvPaidornot.setText(bundle.getString("paidornot"));
            tvCost.setText(bundle.getString("cost"));

        }
        else{
            Toast.makeText(this, "No Message", Toast.LENGTH_LONG).show();
        }

        btnConform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestConform();
            }
        });


    }

    private void requestConform() {


        Rideshare_api rideshareApi = url.getInstance().create(Rideshare_api.class);
        Call<ridepost> voidCall = rideshareApi.updateRide(url.token, id);
        voidCall.enqueue(new Callback<ridepost>() {
            @Override
            public void onResponse(Call<ridepost> call, Response<ridepost> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(PostDetailsActivity.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(PostDetailsActivity.this, "Requested !!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ridepost> call, Throwable t) {
                Toast.makeText(PostDetailsActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

}

