package com.ieeecsvit.riviera17android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.models.Event;
import com.ieeecsvit.riviera17android.models.Events;
import com.ieeecsvit.riviera17android.models.MessageRequest;
import com.ieeecsvit.riviera17android.models.MessagesResponse;
import com.ieeecsvit.riviera17android.rest.ApiClient;
import com.ieeecsvit.riviera17android.rest.ApiInterface;
import com.ieeecsvit.riviera17android.rest.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMessageActivity extends AppCompatActivity {

    Button btSendTo, btSend;
    EditText etMessage;
    TextView tvSendTo;
    String sendTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        sendTo = "all";

        btSendTo = (Button) findViewById(R.id.bt_add_event);
        btSend = (Button) findViewById(R.id.bt_send);

        etMessage = (EditText) findViewById(R.id.et_message);

        tvSendTo = (TextView) findViewById(R.id.tv_send_to);

        btSendTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SendMessageActivity.this, EventSearchActivity.class);
                startActivityForResult(i, 1);
            }
        });

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageRequest messageRequest = new MessageRequest();
                messageRequest.setMessage(etMessage.getText().toString());
                messageRequest.setTo(sendTo);

                ApiInterface apiInterface = new ApiClient().getClient(SendMessageActivity.this).create(ApiInterface.class);
                Call<MessagesResponse> call = apiInterface.postMessage(messageRequest);

                call.enqueue(new Callback<MessagesResponse>() {
                    @Override
                    public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<MessagesResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            String result = data.getStringExtra("eventId");
            Event event = RealmController.with(SendMessageActivity.this).getEvent(result);
            tvSendTo.setText(event.eventName);
            sendTo = result;
        }
    }
}
