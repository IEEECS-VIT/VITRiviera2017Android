package com.ieeecsvit.riviera17android.rest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.ieeecsvit.riviera17android.Consts;
import com.ieeecsvit.riviera17android.models.LoginRequest;
import com.ieeecsvit.riviera17android.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Karishnu Poddar on 14/10/2016.
 */
public class Auth {
    public static void login(String username, String password, final Activity activity) {
        ApiInterface apiInterface = new ApiClient().getClient(activity).create(ApiInterface.class);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.username = username;
        loginRequest.password = password;

        Call<LoginResponse> login = apiInterface.login(loginRequest);

        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                SharedPreferences.Editor editor = activity.getSharedPreferences(Consts.PREF_NAME, Context.MODE_PRIVATE).edit();
                editor.putString(Consts.TOKEN_PREF, response.body().token);
                editor.apply();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

    public static String getToken(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Consts.PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(Consts.TOKEN_PREF,"notfound");
    }
}
