package com.ieeecsvit.riviera17android.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.ieeecsvit.riviera17android.CustomLinearLayoutManager;
import com.ieeecsvit.riviera17android.R;
import com.ieeecsvit.riviera17android.RVChangeResponseAdapter;
import com.ieeecsvit.riviera17android.models.ChangeResponse;
import com.ieeecsvit.riviera17android.rest.ApiClient;
import com.ieeecsvit.riviera17android.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.rvNotifications);
        RecyclerView.LayoutManager linearLayoutManager = new CustomLinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        ApiInterface apiInterface = new ApiClient().getClient(this).create(ApiInterface.class);
        Call<ChangeResponse> changeResponseCall = apiInterface.getChanges();

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Downloading change requests. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        changeResponseCall.enqueue(new Callback<ChangeResponse>() {
            @Override
            public void onResponse(Call<ChangeResponse> call, Response<ChangeResponse> response) {
                dialog.hide();
                recyclerView.setAdapter(new RVChangeResponseAdapter(response.body().data, NotificationsActivity.this));
            }

            @Override
            public void onFailure(Call<ChangeResponse> call, Throwable t) {
                dialog.hide();
            }
        });
     }
}
