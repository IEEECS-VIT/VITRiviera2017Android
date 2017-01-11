package com.ieeecsvit.riviera17android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.ieeecsvit.riviera17android.models.Change;
import com.ieeecsvit.riviera17android.models.ChangeRequest;
import com.ieeecsvit.riviera17android.models.Event;
import com.ieeecsvit.riviera17android.models.EventCoordinator;
import com.ieeecsvit.riviera17android.models.LoginResponse;
import com.ieeecsvit.riviera17android.rest.ApiClient;
import com.ieeecsvit.riviera17android.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoodDetailsEditFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;

    EditText etCood1Name, etCood1Reg, etCood1Email, etCood1Phone, etCood2Name, etCood2Reg, etCood2Email, etCood2Phone;
    Button btSave;
    Event event;

    public CoodDetailsEditFragment() {}

    public static CoodDetailsEditFragment newInstance(String param1) {
        CoodDetailsEditFragment fragment = new CoodDetailsEditFragment();
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

        View v = inflater.inflate(R.layout.fragment_cood_details_edit, container, false);

        etCood1Name = (EditText) v.findViewById(R.id.et_coord_1_name);
        etCood1Reg = (EditText) v.findViewById(R.id.et_coord_1_regno);
        etCood1Email = (EditText) v.findViewById(R.id.et_coord_1_email);
        etCood1Phone = (EditText) v.findViewById(R.id.et_coord_1_phone);

        etCood2Name = (EditText) v.findViewById(R.id.et_coord_2_name);
        etCood2Reg = (EditText) v.findViewById(R.id.et_coord_2_regno);
        etCood2Email = (EditText) v.findViewById(R.id.et_coord_2_email);
        etCood2Phone = (EditText) v.findViewById(R.id.et_coord_2_phone);

        btSave = (Button) v.findViewById(R.id.btSave);

        etCood1Name.setText(event.eventCoordinators.get(0).name);
        etCood1Reg.setText(event.eventCoordinators.get(0).regNo);
        etCood1Email.setText(event.eventCoordinators.get(0).email);
        etCood1Phone.setText(event.eventCoordinators.get(0).phone);

        etCood2Name.setText(event.eventCoordinators.get(1).name);
        etCood2Reg.setText(event.eventCoordinators.get(1).regNo);
        etCood2Email.setText(event.eventCoordinators.get(1).email);
        etCood2Phone.setText(event.eventCoordinators.get(1).phone);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventCoordinator eventCoordinator1 = new EventCoordinator();
                eventCoordinator1.name = etCood1Name.getText().toString();
                eventCoordinator1.regNo = etCood1Reg.getText().toString();
                eventCoordinator1.email = etCood1Email.getText().toString();
                eventCoordinator1.phone = etCood1Phone.getText().toString();

                EventCoordinator eventCoordinator2 = new EventCoordinator();
                eventCoordinator2.name = etCood2Name.getText().toString();
                eventCoordinator2.regNo = etCood2Reg.getText().toString();
                eventCoordinator2.email = etCood2Email.getText().toString();
                eventCoordinator2.phone = etCood2Phone.getText().toString();

                List<EventCoordinator> eventCoordinators = new ArrayList<>();
                eventCoordinators.add(eventCoordinator1);
                eventCoordinators.add(eventCoordinator2);

                Gson gson = new Gson();
                Change changeCood = new Change();
                changeCood.changeField = "event_coordinators";
                changeCood.changeValue = gson.toJson(eventCoordinators);

                ChangeRequest changeRequest = new ChangeRequest();
                List<Change> changes = new ArrayList<>();
                changes.add(changeCood);
                changeRequest.eventId = Long.parseLong(event.id);
                changeRequest.changes = changes;

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