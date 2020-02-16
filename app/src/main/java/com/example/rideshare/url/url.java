package com.example.rideshare.url;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class url {
        //ip for emulator
  public static final String BASE_URL = "http://10.0.2.2:3001/";

//public static  final String BASE_URL = "http://192.168.10.194:3001/";
    //ip for mobile

// public static final String BASE_URL = "http://172.26.1.128:3000/";

    public static String token = "Bearer ";

    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit;

    }
}
