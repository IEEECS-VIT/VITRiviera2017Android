package com.ieeecsvit.riviera17android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ieeecsvit.riviera17android.R;
import com.ieeecsvit.riviera17android.RealmController;
import com.ieeecsvit.riviera17android.models.Change;
import com.ieeecsvit.riviera17android.models.ChangeRequest;
import com.ieeecsvit.riviera17android.models.Event;
import com.ieeecsvit.riviera17android.models.LoginResponse;
import com.ieeecsvit.riviera17android.rest.ApiClient;
import com.ieeecsvit.riviera17android.rest.ApiInterface;
import com.ieeecsvit.riviera17android.utility.Consts;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventEditActivity extends AppCompatActivity {

    EditText etEventName, etEventDesc, etEventRules;
    Button btSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);

        etEventName = (EditText) findViewById(R.id.etEventName);
        etEventDesc = (EditText) findViewById(R.id.etEventDesc);
        etEventRules = (EditText) findViewById(R.id.etEventRules);

        btSave = (Button) findViewById(R.id.btSave);

        final Event event =  RealmController.with(this).getEvent(getIntent().getStringExtra(Consts.EVENT_BUNDLE));
        etEventName.setText(event.eventName);
        etEventDesc.setText(event.eventDescription);
        etEventRules.setText(event.eventRules);

        final Change changeName = new Change();
        final Change changeDesc = new Change();
        final Change changeRules = new Change();
        final ChangeRequest changeRequest = new ChangeRequest();
        changeRequest.eventId = Long.parseLong(event.id);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etEventName.equals(event.eventName)){
                    changeName.changeField = "event_name";
                    changeName.changeValue = etEventName.getText().toString();
                    changeRequest.changes.add(changeName);
                }
                if(!etEventDesc.equals(event.eventDescription)){
                    changeDesc.changeField = "event_description";
                    changeDesc.changeValue = etEventDesc.getText().toString();
                    changeRequest.changes.add(changeDesc);
                }
                if(!etEventRules.equals(event.eventRules)){
                    changeRules.changeField = "event_rules";
                    changeRules.changeValue = etEventRules.getText().toString();
                    changeRequest.changes.add(changeRules);
                }

                ApiInterface apiInterface = new ApiClient().getClient(EventEditActivity.this).create(ApiInterface.class);
                Call<LoginResponse> call = apiInterface.changeEvent(changeRequest);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}
