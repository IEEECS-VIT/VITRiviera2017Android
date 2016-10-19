package com.ieeecsvit.riviera17android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//Our class extending fragment
public class EventCoordinatorFragment extends Fragment {

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("CREATED: ", "EVENT COOD");
        return inflater.inflate(R.layout.fragment_eventcoordinator, container, false);
    }
}