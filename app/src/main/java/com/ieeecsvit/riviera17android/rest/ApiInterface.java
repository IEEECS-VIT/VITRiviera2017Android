package com.ieeecsvit.riviera17android.rest;

import com.ieeecsvit.riviera17android.models.Events;
import com.ieeecsvit.riviera17android.models.LoginRequest;
import com.ieeecsvit.riviera17android.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("events/all")
    Call<Events> events();

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}