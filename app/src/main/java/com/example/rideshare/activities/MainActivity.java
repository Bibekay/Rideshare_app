package com.example.rideshare.activities;


import android.app.Notification;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.rideshare.R;

import com.example.rideshare.bll.LoginBLL;
import com.example.rideshare.strictmode.StrictModeClass;
import com.example.rideshare.url.url;


public class MainActivity extends AppCompatActivity{
    private Button btnSignin, btnsignup;
     private EditText etusername, etpassword;
     Vibrator vibrate;
    NotificationManagerCompat notificationManagerCompat;
    Broadcast broadcast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etusername = findViewById(R.id.etusername);
        etpassword = findViewById(R.id.etpassword);
        vibrate = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        btnSignin = findViewById(R.id.btnSignin);
        btnsignup = findViewById(R.id.btnsignup);


        broadcast = new Broadcast();
        notificationManagerCompat = NotificationManagerCompat.from(this);
        Channel channel = new Channel(this);
        channel.createChannel();

        displayNotification1();
        displayNotification2();

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSignup();

            }

        });

        SharedPreferences sharedPreferences = getSharedPreferences("Ride",MODE_PRIVATE);
        String token = sharedPreferences.getString("token","empty");
        if(!token.equals("empty")){
            url.token = token;
            Intent intent = new Intent(MainActivity.this, UserDashboardActivity.class);
            startActivity(intent);
        }


        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///validation for login
                String username = etusername.getText().toString();

                if(TextUtils.isEmpty(username)) {
                    etusername.setError("Enter username");
                    return;
                }

                String password = etpassword.getText().toString();

                if(TextUtils.isEmpty(password)) {
                    etpassword.setError("Enter password");
                    return;
                }
                login();
            }
        });




    }

    private void displayNotification1() {

        Notification notification = new NotificationCompat.Builder(this, Channel.channel_2)
                .setSmallIcon(R.drawable.wifi_working)
                .setContentTitle("Connected")
                .setContentText("Internet is working")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }




    private void displayNotification2() {

        Notification notification = new NotificationCompat.Builder(this, Channel.channel_1)
                .setSmallIcon(R.drawable.wifi_notworking)
                .setContentTitle("Disconnected")
                .setContentText("Internet is not working")
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();

        notificationManagerCompat.notify(2, notification);
    }



    //intent for open signin if not login

    public void openSignup(){
        Intent openSignup = new Intent(this, SignupActivity.class);
        startActivity(openSignup);
    }

    //method login to go to user dashboard activity

    private void  login() {
        //final String name, pass;
       String username =  etusername.getText().toString();
        String password = etpassword.getText().toString();

        LoginBLL loginBLL = new LoginBLL();

        StrictModeClass.StrictMode();
        if(loginBLL.checkUser(username, password)){
            SharedPreferences sharedPreferences = getSharedPreferences("Ride",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("token", url.token);
            editor.commit();
            Intent intent = new Intent(MainActivity.this, UserDashboardActivity.class);
            startActivity(intent);
            finish();

        } else
        {
            Toast.makeText(this, "Username or Password doesnot match", Toast.LENGTH_SHORT).show();
            vibrate.vibrate(1000);
            etusername.requestFocus();
        }

    }


    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcast,intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(broadcast);
    }







}
