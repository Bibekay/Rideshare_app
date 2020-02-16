package com.example.rideshare.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.hardware.usb.UsbRequest;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rideshare.R;
import com.example.rideshare.api.Rideshare_api;
import com.example.rideshare.model.users;
import com.example.rideshare.serverresponse.ImageResponse;
import com.example.rideshare.strictmode.StrictModeClass;
import com.example.rideshare.url.url;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserupdateActivity extends AppCompatActivity {

    EditText etFullname, etcontact, etEmail,etBirthyear, etGender;
    TextView tvUsername;
    Button btnUpdateprofile;
    ImageView ivProfile;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 200;

    String imagePath,imageName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userupdate);

        tvUsername = findViewById(R.id.tvUsername);
        etFullname = findViewById(R.id.etFullname);
        etcontact = findViewById(R.id.etcontact);
        etEmail = findViewById(R.id.etEmail);
        etBirthyear = findViewById(R.id.etBirthyear);
        etGender = findViewById(R.id.etGender);
        ivProfile = findViewById(R.id.ivProfile);


        btnUpdateprofile = findViewById(R.id.btnUpdateprofile);

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        loadCurrentUser();
        btnUpdateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadFile();
                update();
            }
        });

    }



    private void chooseImage(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data!=null) {

                    Uri uri = data.getData();
                    ivProfile.setImageURI(uri);
                    imagePath = getPath(uri);

                } else {
                    Toast.makeText(UserupdateActivity.this, "Please select an image ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(UserupdateActivity.this, "image couldnot upload", Toast.LENGTH_SHORT).show();
            }
        }



    private String getPath(Uri uri){
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(UserupdateActivity.this.getApplicationContext(), uri, proj, null, null, null);

        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String res = cursor.getString(colIndex);
        cursor.close();
        return res;
    }



    private void uploadFile(){
        if(imagePath!=null){
            File file = new File(imagePath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);

            Rideshare_api rideshareApi = url.getInstance().create(Rideshare_api.class);
            Call<ImageResponse> imageResponseCall = rideshareApi.uploadImage(url.token, body);

            StrictModeClass.StrictMode();

            try {
                Response<ImageResponse> imageResponseResponse = imageResponseCall.execute();
                imageName = imageResponseResponse.body().getFilename();
            }catch (IOException e){
                Toast.makeText(UserupdateActivity.this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }else {
            Toast.makeText(UserupdateActivity.this, "Please choose file to update picture", Toast.LENGTH_SHORT).show();
        }

    }




    private void update() {



        if(imageName!=null){
            if(etFullname.getText().toString() != null && etcontact.getText().toString() != null && etEmail.getText().toString() != null && etBirthyear.getText().toString()!=null  && etGender.getText().toString()!=null ){
                users users = new users(etFullname.getText().toString(), etcontact.getText().toString(), etEmail.getText().toString(), etBirthyear.getText().toString(),etGender.getText().toString(), imageName, 1);
                Rideshare_api rideshareApi = url.getInstance().create(Rideshare_api.class);
                Call<users> updateInfoCall = rideshareApi.updateUser(url.token, users);
                System.out.println(url.token);

                updateInfoCall.enqueue(new Callback<users>() {
                    @Override
                    public void onResponse(Call<users> call, Response<users> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(UserupdateActivity.this, "Error : Failed to update info" , Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(UserupdateActivity.this, "Information updated successfully", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<users> call, Throwable t) {
                        Toast.makeText(UserupdateActivity.this, "Error : "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                Toast.makeText(UserupdateActivity.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if(etFullname.getText().toString() != null && etcontact.getText().toString() != null && etEmail.getText().toString() != null && etBirthyear.getText().toString()!=null  && etGender.getText().toString()!=null){
                users users = new users(etFullname.getText().toString(), etcontact.getText().toString(), etEmail.getText().toString(), etBirthyear.getText().toString(), etGender.getText().toString(), 2);
                Rideshare_api rideshareApi = url.getInstance().create(Rideshare_api.class);
                Call<users> updateInfoCall = rideshareApi.updateUser(url.token, users);

                updateInfoCall.enqueue(new Callback<users>() {
                    @Override
                    public void onResponse(Call<users> call, Response<users> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(UserupdateActivity.this, "Error : Failed to update info" , Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(UserupdateActivity.this, "Information updated successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<users> call, Throwable t) {
                        Toast.makeText(UserupdateActivity.this, "Error : "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                Toast.makeText(UserupdateActivity.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadCurrentUser() {

        Rideshare_api rideshareApi = url.getInstance().create(Rideshare_api.class);
        Call<users> usersCall = rideshareApi.getUserDetails(url.token);

        usersCall.enqueue(new Callback<users>() {
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(UserupdateActivity.this, "code" + response.code(), Toast.LENGTH_SHORT ).show();
                    return;
                }
                tvUsername.setText(response.body().getUsername());
                etFullname.setText(response.body().getFullname());
                etcontact.setText(response.body().getContact());
                etEmail.setText(response.body().getEmail());
                etBirthyear.setText(response.body().getBirthyear());
                etGender.setText(response.body().getGender());
                String imagepath = url.BASE_URL + response.body().getImage();
                Picasso.get().load(imagepath).into(ivProfile);

            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {
                Toast.makeText(UserupdateActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
