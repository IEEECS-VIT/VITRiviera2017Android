package com.ieeecsvit.riviera17android.rest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.ieeecsvit.riviera17android.LoginActivity;
import com.ieeecsvit.riviera17android.utility.Consts;
import com.ieeecsvit.riviera17android.models.LoginRequest;
import com.ieeecsvit.riviera17android.models.LoginResponse;
import com.ieeecsvit.riviera17android.utility.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Karishnu Poddar on 14/10/2016.
 */
public class Auth {
    public static void login(String username, String password, final Activity activity, final OnLoginCallback onLoginCallback) {
        ApiInterface apiInterface = new ApiClient().getClient(activity).create(ApiInterface.class);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.username = username;
        loginRequest.password = password;

        Call<LoginResponse> login = apiInterface.login(loginRequest);

        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body()!=null && response.body().success) {
                    Preferences.setPrefs(Consts.TOKEN_PREF, response.body().token, activity);
                    Preferences.setPrefs(Consts.LOGGED_IN_PREF,"1",activity);
                    Preferences.setPrefs(Consts.ROLE_PREF, response.body().role, activity);
                    onLoginCallback.onSuccess();
                }
                else {
                    onLoginCallback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

    static String getToken(Context context) {
        return Preferences.getPrefs(Consts.TOKEN_PREF, context);
    }

    public interface OnLoginCallback {
        void onSuccess();

        void onFailure();
    }
}
