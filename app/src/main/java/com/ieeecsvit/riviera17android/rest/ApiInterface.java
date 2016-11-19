package com.ieeecsvit.riviera17android.rest;

import com.ieeecsvit.riviera17android.models.ChangeRequest;
import com.ieeecsvit.riviera17android.models.ChangeResponse;
import com.ieeecsvit.riviera17android.models.Events;
import com.ieeecsvit.riviera17android.models.LoginRequest;
import com.ieeecsvit.riviera17android.models.LoginResponse;
import com.ieeecsvit.riviera17android.models.MessageRequest;
import com.ieeecsvit.riviera17android.models.MessagesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
/*    @GET("events/all")
    Call<Events> events();*/

    @GET("events/all")
    Call<Events> events();

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("messages")
    Call<MessagesResponse> getMessages();

    @POST("messages")
    Call<MessagesResponse> postMessage(@Body MessageRequest messageRequest);

    @POST("requests")
    Call<LoginResponse> changeEvent(@Body ChangeRequest changeRequest);

    @GET("requests")
    Call<ChangeResponse> getChanges();
}