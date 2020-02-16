package com.example.rideshare.bll;

import com.example.rideshare.api.Rideshare_api;
import com.example.rideshare.serverresponse.SignUpResponse;
import com.example.rideshare.url.url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {

    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {

        Rideshare_api rideshareApi = url.getInstance().create(Rideshare_api.class);
        Call<SignUpResponse> usersCall = rideshareApi.checkUser(username, password);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login Successful")) {

                url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
