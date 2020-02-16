package com.example.rideshare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rideshare.R;

import com.example.rideshare.api.Rideshare_api;
import com.example.rideshare.model.users;
import com.example.rideshare.serverresponse.SignUpResponse;
import com.example.rideshare.url.url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    EditText etfullname, etusername, etcontact, etemail, etpassword;
    Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etfullname = findViewById(R.id. etfullname);
        etusername = findViewById(R.id. etusername);
        etcontact = findViewById(R.id.etcontact);
        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        register = findViewById(R.id.btnregister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                      String fullname = etfullname.getText().toString();
                      String username =   etusername.getText().toString();
                      String contact = etcontact.getText().toString();
                      String email =  etemail.getText().toString();
                      String password = etpassword.getText().toString();

                users users = new users(fullname, username, contact, email,password);

                Rideshare_api rideshareApi = url.getInstance().create(Rideshare_api.class);
                Call<SignUpResponse> registerCall = rideshareApi.signup(users);

                registerCall.enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(SignupActivity.this, "Registered Successfull", Toast.LENGTH_SHORT).show();
                        Intent signupSucess = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(signupSucess);
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        Toast.makeText(SignupActivity.this, "Error:" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });




        Button btnAlreadylogin = (Button) findViewById(R.id.btnAlreadylogin);
                btnAlreadylogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                });

            }
        }

