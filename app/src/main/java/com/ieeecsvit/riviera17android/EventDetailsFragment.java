package com.ieeecsvit.riviera17android;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.models.Event;

public class EventDetailsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    Event event;
    TextView tvEventDesc, tvEventRules, tvEventName, tvEventCategory, tvEventFees;

    public EventDetailsFragment() {
        // Required empty public constructor
    }

    public static EventDetailsFragment newInstance(String eventId) {
        EventDetailsFragment fragment = new EventDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, eventId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.event =  RealmController.with(getActivity()).getEvent(getArguments().getString(ARG_PARAM1));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_event_details, container, false);

        tvEventDesc = (TextView) v.findViewById(R.id.tv_event_description);
        tvEventRules = (TextView) v.findViewById(R.id.tv_event_rules);
        tvEventName = (TextView) v.findViewById(R.id.tv_event_name);
        tvEventCategory = (TextView) v.findViewById(R.id.tv_event_category);
        tvEventFees = (TextView) v.findViewById(R.id.tv_event_fees);

        tvEventFees.setText(event.eventRegFees);
        tvEventCategory.setText(event.eventCategory);
        tvEventName.setText(event.eventName);
        tvEventDesc.setText(event.eventDescription);
        tvEventRules.setText(event.eventRules);

        return v;
    }
}
