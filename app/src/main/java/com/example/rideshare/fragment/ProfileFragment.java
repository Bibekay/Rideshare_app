package com.example.rideshare.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rideshare.R;
import com.example.rideshare.activities.BikedetailsActivity;
import com.example.rideshare.activities.MainActivity;
import com.example.rideshare.activities.MapsActivity;
import com.example.rideshare.activities.UserupdateActivity;
import com.example.rideshare.url.url;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {



    public static ProfileFragment getInstance() { return new ProfileFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        //to view next activity bike deatils activity layout
        Button btnAddbike = view.findViewById(R.id.btnAddbike);
        btnAddbike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openBikedetails = new Intent(getActivity(), BikedetailsActivity.class);
                startActivity(openBikedetails);
            }
        });




        //to view next activity map activity layout
        Button btnOpenlocation = view.findViewById(R.id.btnOpenlocation);
        btnOpenlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent openMap = new Intent(getActivity(), MapsActivity.class);
//                startActivity(openMap);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MapsActivity()).commit();
            }
        });

        //to view next activity profile update layout

        Button btnAboutprofile = view.findViewById(R.id.btnAboutprofile);
        btnAboutprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openUpdateprofile = new Intent(getActivity(), UserupdateActivity.class);
                startActivity(openUpdateprofile);
            }
        });


        //used for user signing out to login activity with dialog box

        Button btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(false);
                builder.setMessage("Do you want to Logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Ride",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("token");
                        editor.commit();
                        url.token = "Bearer ";
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialouge box no to cancel lagout to continue with app.
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

        });

        return view;
    }
}
