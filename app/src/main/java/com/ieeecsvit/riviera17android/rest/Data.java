package com.ieeecsvit.riviera17android.rest;

import android.app.Activity;
import android.util.Log;

import com.ieeecsvit.riviera17android.RealmController;
import com.ieeecsvit.riviera17android.models.Event;
import com.ieeecsvit.riviera17android.models.Events;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Karishnu Poddar on 15/10/2016.
 */
public class Data {
    public static void updateEvents(final Activity activity){

        ApiInterface apiInterface = new ApiClient().getClient(activity).create(ApiInterface.class);
        Call<Events> getEvents = apiInterface.events();

        getEvents.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                Realm realm = RealmController.with(activity).getRealm();

                for (Event e : response.body().events) {
                    realm.beginTransaction();
                    realm.copyToRealm(e);
                    realm.commitTransaction();
                }
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {

            }
        });
    }
}
