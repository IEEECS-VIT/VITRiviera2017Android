package com.ieeecsvit.riviera17android.rest;

import android.app.Activity;
import android.os.AsyncTask;

import com.ieeecsvit.riviera17android.RealmController;
import com.ieeecsvit.riviera17android.models.Event;
import com.ieeecsvit.riviera17android.models.Events;
import com.ieeecsvit.riviera17android.models.Message;
import com.ieeecsvit.riviera17android.models.MessagesResponse;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Data {
    public static void updateEvents(final Activity activity, final UpdateCallback updateCallback) {
        WriteEventsTask writeEventsTask = new WriteEventsTask(updateCallback);
        writeEventsTask.execute(activity);
    }

    public static void updateMessages(final Activity activity, final UpdateCallback updateCallback) {
        ApiInterface apiInterface = new ApiClient().getClient(activity).create(ApiInterface.class);
        Call<MessagesResponse> getMessages = apiInterface.getMessages();

        getMessages.enqueue(new Callback<MessagesResponse>() {
            @Override
            public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {

                if (response.body().getSuccess()) {
                    RealmController.with(activity).refresh();
                    Realm realm = RealmController.getInstance().getRealm();
                    RealmController.getInstance().clearAllMessages();

                    for (Message e : response.body().getData()) {
                        realm.beginTransaction();
                        realm.copyToRealm(e);
                        realm.commitTransaction();
                    }
                }

                updateCallback.onUpdate();
            }

            @Override
            public void onFailure(Call<MessagesResponse> call, Throwable t) {

            }
        });
    }

    private static class WriteEventsTask extends AsyncTask<Activity, Void, Integer> {

        UpdateCallback updateCallback;

        WriteEventsTask(UpdateCallback updateCallback){
            this.updateCallback = updateCallback;
        }

        @Override
        protected Integer doInBackground(Activity... activities) {
            final Activity activity = activities[0];
            ApiInterface apiInterface = new ApiClient().getClient(activity).create(ApiInterface.class);
            Call<Events> eventsCall = apiInterface.events();
            try {
                List<Event> events = eventsCall.execute().body().events;
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.delete(Event.class);
                realm.commitTransaction();
                for (final Event e : events) {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealm(e);
                        }
                    });
                }
            }catch (Exception e){e.printStackTrace();}
            return 0;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            updateCallback.onUpdate();
        }
    }

    public interface UpdateCallback {
        void onUpdate();
        void onFailure();
    }
}
