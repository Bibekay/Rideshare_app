package com.example.rideshare.api;


import com.example.rideshare.model.bikedetails;
import com.example.rideshare.model.notification;
import com.example.rideshare.model.ridepost;
import com.example.rideshare.model.riderequest;
import com.example.rideshare.model.users;
import com.example.rideshare.serverresponse.ImageResponse;
import com.example.rideshare.serverresponse.SignUpResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Rideshare_api {

    //api for user
    @POST("users/signup")
    Call<SignUpResponse> signup(@Body users users);
    @FormUrlEncoded
    @POST("users/login")
    Call<SignUpResponse> checkUser(@Field("username") String username, @Field("password") String password);


    @Multipart
    @POST ("upload/")
    Call <ImageResponse> uploadImage(@Header("Authorization") String token, @Part MultipartBody.Part file);


    @GET("users/me")
    Call<users> getUserDetails(@Header("Authorization")String token );

    @PUT("users/me")
    Call<users> updateUser(@Header("Authorization")String token,@Body users users);




    //for model ridepost
    @POST("ridepost/ridepost")
    Call<ridepost> rideposts(@Header("Authorization")String token,@Body ridepost ridepost);

    @GET("ridepost/ridedetails")
    Call<List<ridepost>>getRideposts(@Header("Authorization") String token);

    @PUT("ridepost/{id}")
    Call<ridepost> updateRide(@Header("Authorization")String token, @Path("id") String id);

    @GET("ridepost/myPostedrides")
    Call<List<ridepost>>getMyrides(@Header("Authorization") String token);

    @GET("ridepost/myPosts")
    Call<List<ridepost>>myPost(@Header("Authorization") String token);

    @PUT("ridepost/{id}/Update")
    Call<ridepost> updateMyrides(@Header("Authorization")String token, @Path("id") String id, @Body ridepost ridepost);

    @DELETE("ridepost/{id}/delete")
    Call<ridepost>deletePost(@Header("Authorization") String token, @Path("id") String id);



    //for model riderequest
    @FormUrlEncoded
    @POST("riderequest/riderequest")
    Call<riderequest> riderequests(@Header("Authorization")String token,
                                @Field("pickuppoint") String pickuppoint,
                                @Field("destination") String destination,
                                @Field("journeydate") String journeydate,
                                @Field("time") String time,
                                @Field("paidornot") String paidornot,
                                @Field("cost") String cost);



    @GET("riderequest/riderequestdetails")
    Call<List<riderequest>>requestOffer(@Header("Authorization") String token);





    //Model notification

    @POST("notification/notify")
    Call<notification> notifications( @Body notification notify);



    //Model bike_details
    @FormUrlEncoded
    @POST("bikedetails/bikedetails")
    Call<bikedetails> bikeinfo(@Header("Authorization")String token,
                               @Field("model") String model,
                               @Field("color") String color,
                               @Field("plateno") String plateno);



}
