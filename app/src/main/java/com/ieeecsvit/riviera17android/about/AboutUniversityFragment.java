package com.ieeecsvit.riviera17android.about;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ieeecsvit.riviera17android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUniversityFragment extends Fragment {


    public AboutUniversityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_university, container, false);
    }

}
