package com.ieeecsvit.riviera17android.rest;

import com.ieeecsvit.riviera17android.models.Events;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    //@POST("auth")
    //Call<LoginVerify> login(@Body LoginRequest loginRequest);
    @GET("events/all")
    Call<Events> events();

}