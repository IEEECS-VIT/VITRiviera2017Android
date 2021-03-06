package com.ieeecsvit.riviera17android.about;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ieeecsvit.riviera17android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 10/8/16.
 */
public class AboutTeam2Fragment extends Fragment {

    private List<TeamMember> teamMemberList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TeamAdapter teamAdapter;
    private View v;

  /*  public AboutTeam2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_about_team2, container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view_team2);
        teamAdapter = new TeamAdapter(teamMemberList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(teamAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getData();
        recyclerView.setNestedScrollingEnabled(false);
        return v;
    }

    private void getData(){

        String[] names = {"",
                "Dr. G. Viswanathan\nChancellor & Founder\nVIT University",
                "Mr. Sankar Viswanathan\nVice President\nVIT University",
                "Dr. Sekar Viswanathan\nVice President\nVIT University",
                "Mr. G. V. Selvam\nVice President\nVIT University",
                "Dr. Anand A Samuel\nVice Chancellor\nVIT University",
                "Dr. V. Raju\nPro-Vice Chancellor\nVIT University",
                "Dr. S. Narayanan\nPro-Vice Chancellor\nVIT University"
                };
        String[] drawableId = new String[27];
        for (int i=1;i<=7;i++) {
            drawableId[i] = "y" + i;
        }

        TeamMember teamMember;

        for (int i=1;i<=7;i++) {
            int resId = getResources().getIdentifier(drawableId[i],"drawable",getActivity().getPackageName());
            teamMember = new TeamMember(names[i],resId);
            teamMemberList.add(teamMember);
        }

    }
*/
}
