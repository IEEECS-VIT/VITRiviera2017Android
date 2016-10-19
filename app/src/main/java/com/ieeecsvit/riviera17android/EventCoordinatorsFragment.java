package com.ieeecsvit.riviera17android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.models.Event;
import com.ieeecsvit.riviera17android.models.EventCoordinator;

public class EventCoordinatorsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView tvCoordName1, tvCoordRegno1, tvCoordEmail1, tvCoordPhone1;
    TextView tvCoordName2, tvCoordRegno2, tvCoordEmail2, tvCoordPhone2;

    EventCoordinator eventCoordinator1, eventCoordinator2;

    String eventId;

    public EventCoordinatorsFragment() {
        // Required empty public constructor
    }

    public static EventCoordinatorsFragment newInstance(String eventId) {
        EventCoordinatorsFragment fragment = new EventCoordinatorsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, eventId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.eventId = getArguments().getString(ARG_PARAM1);

            Event event =  RealmController.with(getActivity()).getEvent(eventId);

            this.eventCoordinator1 = event.eventCoordinators.get(0);
            this.eventCoordinator2 = event.eventCoordinators.get(1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_event_coordinators, container, false);

        tvCoordName1 = (TextView) v.findViewById(R.id.tv_coordinator_1_name);
        tvCoordName2 = (TextView) v.findViewById(R.id.tv_coordinator_2_name);

        tvCoordRegno1 = (TextView) v.findViewById(R.id.tv_coordinator_1_regno);
        tvCoordRegno2 = (TextView) v.findViewById(R.id.tv_coordinator_2_regno);

        tvCoordEmail1 = (TextView) v.findViewById(R.id.tv_coordinator_1_email);
        tvCoordEmail2 = (TextView) v.findViewById(R.id.tv_coordinator_2_email);

        tvCoordPhone1 = (TextView) v.findViewById(R.id.tv_coordinator_1_phone);
        tvCoordPhone2 = (TextView) v.findViewById(R.id.tv_coordinator_2_phone);

        tvCoordName1.setText(eventCoordinator1.name);
        tvCoordName2.setText(eventCoordinator2.name);

        tvCoordRegno1.setText(eventCoordinator1.regNo);
        tvCoordRegno2.setText(eventCoordinator2.regNo);

        tvCoordEmail1.setText(eventCoordinator1.email);
        tvCoordEmail2.setText(eventCoordinator2.email);

        tvCoordPhone1.setText(eventCoordinator1.phone);
        tvCoordPhone2.setText(eventCoordinator2.phone);

        return v;
    }

}
