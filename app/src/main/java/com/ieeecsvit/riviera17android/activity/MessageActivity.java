package com.ieeecsvit.riviera17android.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ieeecsvit.riviera17android.R;
import com.ieeecsvit.riviera17android.RVMessageAdapter;
import com.ieeecsvit.riviera17android.RealmController;
import com.ieeecsvit.riviera17android.models.MessageRequest;
import com.ieeecsvit.riviera17android.models.MessagesResponse;
import com.ieeecsvit.riviera17android.rest.ApiClient;
import com.ieeecsvit.riviera17android.rest.ApiInterface;
import com.ieeecsvit.riviera17android.rest.Data;
import com.ieeecsvit.riviera17android.utility.Consts;
import com.ieeecsvit.riviera17android.utility.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    RVMessageAdapter rvMessageAdapter;
    Button send;
    EditText message;
    String sendTo;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        sendTo = "all";

        getSupportActionBar().setTitle("Riviera'17");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#68B3D2")));

        recyclerView = (RecyclerView) findViewById(R.id.rv_message);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        send = (Button) findViewById(R.id.sendmsg);
        message = (EditText) findViewById(R.id.msgbox);

        if(Preferences.getPrefs(Consts.ROLE_PREF,this).equals("coordinator")){
            send.setVisibility(View.GONE);
            message.setVisibility(View.GONE);
        }

        swipeRefreshLayout.setOnRefreshListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        rvMessageAdapter = new RVMessageAdapter(RealmController.with(this).getMessages(), this);

        recyclerView.setAdapter(rvMessageAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seealert();
            }
        });

        customRefresh();
    }

    @Override
    public void onRefresh() {
        customRefresh();
    }

    public void seealert() {
        dialog = new Dialog(this);
        dialog.setTitle("Choose");

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.activity_send_message, null);

        Button sendtoall = (Button) view.findViewById(R.id.bt_send);

        sendtoall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendTo = "all";
                sendMessage();
            }
        });

        Button event = (Button) view.findViewById(R.id.bt_add_event);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MessageActivity.this, EventSearchActivity.class);
                startActivityForResult(in, 1);
            }
        });

        dialog.setContentView(view);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            sendTo = data.getStringExtra("eventId");
            sendMessage();
        }
    }

    public void customRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        Data.updateMessages(this, new Data.UpdateCallback() {
            @Override
            public void onUpdate() {
                rvMessageAdapter = new RVMessageAdapter(RealmController.with(MessageActivity.this).getMessages(), MessageActivity.this);
                recyclerView.setAdapter(rvMessageAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void sendMessage(){
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setMessage(message.getText().toString());
        messageRequest.setTo(sendTo);

        ApiInterface apiInterface = new ApiClient().getClient(MessageActivity.this).create(ApiInterface.class);
        Call<MessagesResponse> call = apiInterface.postMessage(messageRequest);

        dialog.dismiss();

        final ProgressDialog progressdialog = new ProgressDialog(this);
        progressdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressdialog.setMessage("Sending Messaging. Please wait...");
        progressdialog.setIndeterminate(true);
        progressdialog.setCanceledOnTouchOutside(false);
        progressdialog.show();

        call.enqueue(new Callback<MessagesResponse>() {
            @Override
            public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                customRefresh();
                progressdialog.hide();
                message.setText("");
            }

            @Override
            public void onFailure(Call<MessagesResponse> call, Throwable t) {
                progressdialog.hide();
            }
        });
    }
}