package com.ieeecsvit.riviera17android;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ieeecsvit.riviera17android.models.Event;
import com.ieeecsvit.riviera17android.models.Message;
import com.ieeecsvit.riviera17android.models.MessageRequest;
import com.ieeecsvit.riviera17android.models.MessagesResponse;
import com.ieeecsvit.riviera17android.rest.ApiClient;
import com.ieeecsvit.riviera17android.rest.ApiInterface;
import com.ieeecsvit.riviera17android.rest.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    RecyclerView recyclerView;
   // FloatingActionButton floatingActionButton;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RVMessageAdapter rvMessageAdapter;
    Button send;
    EditText message;
    String sendTo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        sendTo = "all";

        recyclerView = (RecyclerView) findViewById(R.id.rv_message);
       // floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_add_message);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        send=(Button)findViewById(R.id.sendmsg);
        message=(EditText)findViewById(R.id.msgbox);

        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rvMessageAdapter = new RVMessageAdapter(RealmController.with(this).getMessages(), this);

        recyclerView.setAdapter(rvMessageAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seealert();
            }
        });

        /*
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessageActivity.this, SendMessageActivity.class);
                startActivity(intent);
            }
        });
        */
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        Data.updateMessages(this, new Data.UpdateCallback() {
            @Override
            public void onUpdate() {
                rvMessageAdapter = new RVMessageAdapter(RealmController.with(MessageActivity.this).getMessages(), MessageActivity.this);
                recyclerView.setAdapter(rvMessageAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(){}
        });
    }


    public void seealert(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Choose..");
       // builder.setCancelable(false);


        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.activity_send_message,null);

        Button sendtoall=(Button)view.findViewById(R.id.bt_send);

        sendtoall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageRequest messageRequest = new MessageRequest();
                messageRequest.setMessage(message.getText().toString());
                messageRequest.setTo(sendTo);

                ApiInterface apiInterface = new ApiClient().getClient(MessageActivity.this).create(ApiInterface.class);
                Call<MessagesResponse> call = apiInterface.postMessage(messageRequest);

                call.enqueue(new Callback<MessagesResponse>() {
                    @Override
                    public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<MessagesResponse> call, Throwable t) {

                    }
                });
            }
        });

        Button event=(Button)view.findViewById(R.id.bt_add_event);


        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MessageActivity.this, EventSearchActivity.class);
                startActivityForResult(in, 1);
            }
        });
        builder.setView(view);
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            String result = data.getStringExtra("eventId");
            Event event = RealmController.with(MessageActivity.this).getEvent(result);
            //tvSendTo.setText(event.eventName);
            sendTo = result;
        }
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

}
