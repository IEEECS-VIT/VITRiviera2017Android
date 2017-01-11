package com.ieeecsvit.riviera17android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ieeecsvit.riviera17android.models.Change;
import com.ieeecsvit.riviera17android.models.ChangeRequest;
import com.ieeecsvit.riviera17android.models.Event;
import com.ieeecsvit.riviera17android.models.LoginResponse;
import com.ieeecsvit.riviera17android.rest.ApiClient;
import com.ieeecsvit.riviera17android.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailsEditFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    EditText etEventName, etEventDesc, etEventRules;
    Button btSave;
    Event event;

    public EventDetailsEditFragment() {}

    public static EventDetailsEditFragment newInstance(String param1) {
        EventDetailsEditFragment fragment = new EventDetailsEditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            this.event =  RealmController.with(this).getEvent(mParam1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_event_details_edit, container, false);

        etEventName = (EditText) v.findViewById(R.id.etEventName);
        etEventDesc = (EditText) v.findViewById(R.id.etEventDesc);
        etEventRules = (EditText) v.findViewById(R.id.etEventRules);

        btSave = (Button) v.findViewById(R.id.btSave);

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
                if(!etEventName.getText().toString().equals(event.eventName)){
                    changeName.changeField = "event_name";
                    changeName.changeValue = etEventName.getText().toString();
                    changeRequest.changes.add(changeName);
                }
                if(!etEventDesc.getText().toString().equals(event.eventDescription)){
                    changeDesc.changeField = "event_description";
                    changeDesc.changeValue = etEventDesc.getText().toString();
                    changeRequest.changes.add(changeDesc);
                }
                if(!etEventRules.getText().toString().equals(event.eventRules)){
                    changeRules.changeField = "event_rules";
                    changeRules.changeValue = etEventRules.getText().toString();
                    changeRequest.changes.add(changeRules);
                }

                ApiInterface apiInterface = new ApiClient().getClient(getActivity()).create(ApiInterface.class);
                Call<LoginResponse> call = apiInterface.changeEvent(changeRequest);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        getActivity().finish();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
            }
        });

        return v;
    }
}