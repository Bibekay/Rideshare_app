package com.example.rideshare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rideshare.R;
import com.example.rideshare.api.Rideshare_api;
import com.example.rideshare.model.bikedetails;
import com.example.rideshare.url.url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BikedetailsActivity extends AppCompatActivity {
    EditText etBikemodel, etBikecolor, etPlateno;
    Button btnRegisterbike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bikedetails);


        etBikemodel = findViewById(R.id.etBikemodel);
        etBikecolor = findViewById(R.id.etBikecolor);
        etPlateno = findViewById(R.id.etPlateno);
        btnRegisterbike = findViewById(R.id.btnRegisterbike);

        btnRegisterbike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBikedetails();
                etBikemodel.setText("");
                etBikecolor.setText("");
                etPlateno.setText("");

            }
        });
    }

    private void addBikedetails(){
        Rideshare_api rideshareApi = url.getInstance().create(Rideshare_api.class);
        Call<bikedetails> voidCall = rideshareApi.bikeinfo(url.token,
                etBikemodel.getText().toString(),
                etBikecolor.getText().toString(),
                etPlateno.getText().toString());

        voidCall.enqueue(new Callback<bikedetails>() {
            @Override
            public void onResponse(Call<bikedetails> call, Response<bikedetails> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(BikedetailsActivity.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(BikedetailsActivity.this, "Post successful !!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<bikedetails> call, Throwable t) {
                Toast.makeText(BikedetailsActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
