package com.example.rideshare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rideshare.R;
import com.example.rideshare.api.Rideshare_api;
import com.example.rideshare.model.riderequest;
import com.example.rideshare.url.url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickrideActivity extends AppCompatActivity {
        EditText etRequestpickpoint, etRequestDestinationpoint, etRequestdate, etRequesttime, etRequestridetype, etRequestcost;
        Button btnRiderequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickride);

        etRequestpickpoint = findViewById(R.id.etRequestpickpoint);
        etRequestDestinationpoint = findViewById(R.id.etRequestDestinationpoint);
        etRequestdate = findViewById(R.id.etRequestdate);
        etRequesttime = findViewById(R.id.etRequesttime);
        etRequestridetype = findViewById(R.id.etRequestridetype);
        etRequestcost = findViewById(R.id.etRequestcost);
        btnRiderequest = findViewById(R.id.btnRiderequest);

        btnRiderequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (validate()){

                    requestRide();
                    etRequestpickpoint.setText("");
                    etRequestDestinationpoint.setText("");
                    etRequestdate.setText("");
                    etRequesttime.setText("");
                    etRequestridetype.setText("");
                    etRequestcost.setText("");

                }

            }
        });
    }


    private boolean validate(){
        boolean status = true;
        //Validation for offerride
        final String pickuppoint=etRequestpickpoint.getText().toString();
        if(pickuppoint.length()==0 && !pickuppoint.matches("[a-zA-Z ]+"))

        {
            etRequestpickpoint.requestFocus();
            etRequestpickpoint.setError("Pickup point is empty!!!");
            status = false;
        }

        final String destination=etRequestDestinationpoint.getText().toString();
        if(destination.length()==0 && !destination.matches("[a-zA-Z ]+"))

        {
            etRequestDestinationpoint.requestFocus();
            etRequestDestinationpoint.setError("Invalid input!!!");
            status = false;
        }

        final String date=etRequestdate.getText().toString();
        if(date.length()==0 )

        {
            etRequestdate.requestFocus();
            etRequestdate.setError("Invalid input!!!");
            status = false;
        }

        final String type=etRequestridetype.getText().toString();
        if(type.length()==0 && !type.matches("[a-zA-Z ]+"))

        {
            etRequestridetype.requestFocus();
            etRequestridetype.setError("Invalid input!!!");
            status = false;
        }


        final String time=etRequesttime.getText().toString();
        if(time.length()==0 )

        {
            etRequesttime.requestFocus();
            etRequesttime.setError("Invalid input!!!");
            status = false;
        }

        return status;

    }

            private void requestRide(){

                Rideshare_api rideshareApi = url.getInstance().create(Rideshare_api.class);

                Call<riderequest> voidcall = rideshareApi.riderequests(url.token,
                        etRequestpickpoint.getText().toString(),
                        etRequestDestinationpoint.getText().toString(),
                        etRequestdate.getText().toString(),
                        etRequesttime.getText().toString(),
                        etRequestridetype.getText().toString(),
                        etRequestcost.getText().toString());
                voidcall.enqueue(new Callback<riderequest>() {
                    @Override
                    public void onResponse(Call<riderequest> call, Response<riderequest> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(PickrideActivity.this, "Code"+ response.code() +  ", Message :" + response.message(), Toast.LENGTH_SHORT ).show();
                            return;
                        }
                        Toast.makeText(PickrideActivity.this, "Request Posted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<riderequest> call, Throwable t) {
                        Toast.makeText(PickrideActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



    }



}
