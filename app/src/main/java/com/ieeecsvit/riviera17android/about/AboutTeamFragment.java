package com.ieeecsvit.riviera17android.about;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ieeecsvit.riviera17android.R;

import java.util.ArrayList;
import java.util.List;

public class AboutTeamFragment extends Fragment {

    private List<TeamMember> teamMemberList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TeamAdapter teamAdapter;
    private View v;

    public AboutTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_about_team, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_team);
        teamAdapter = new TeamAdapter(teamMemberList, getActivity());
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(teamAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getData();
        recyclerView.setNestedScrollingEnabled(false);
        return v;
    }

    private void getData() {

        teamMemberList.add(new TeamMember("Rishabh Chaddha", "13BCE0034", R.drawable.x0, "https://www.facebook.com/rishabh.chaddha.12"));
        teamMemberList.add(new TeamMember("Harsh Khara", "13BCL0139", R.drawable.x1, "https://www.facebook.com/harshkhara"));
        teamMemberList.add(new TeamMember("Abhinav Sharma", "13BCE0680", R.drawable.x2, "https://www.facebook.com/s.abhinav94"));
        teamMemberList.add(new TeamMember("Harshal Varday", "14BCE0751", R.drawable.x3, "https://github.com/hvarday"));
        teamMemberList.add(new TeamMember("Tushar Narula", "14BCE0336", R.drawable.x4, "https://github.com/tusharnarula"));
        teamMemberList.add(new TeamMember("Waris Chutani", "14BIT0155", R.drawable.x5, "https://github.com/waris0023"));
        teamMemberList.add(new TeamMember("Saurabh Mathur", "14BIT0180", R.drawable.x6, "https://github.com/saurabhmathur96"));
        teamMemberList.add(new TeamMember("Himanshu Jain", "14BCE0531", R.drawable.x7, "https://github.com/himanshujain71"));
        teamMemberList.add(new TeamMember("Karishnu Poddar", "15BCE0318", R.drawable.x8, "https://github.com/karishnu"));
        teamMemberList.add(new TeamMember("Vishwajeetsinh Jadeja", "15BIT0136", R.drawable.x9, "https://github.com/Vishwajeetsinh98"));
        teamMemberList.add(new TeamMember("Akanshi Srivastava", "15BCE0325", R.drawable.x10, "https://github.com/Akanshi32"));
        teamMemberList.add(new TeamMember("Mayank Aggarwal", "15BCE0751", R.drawable.x11, "https://github.com/mayankagg9722"));
        teamMemberList.add(new TeamMember("Vishwash Tilala", "15BME0007", R.drawable.x12, "https://www.facebook.com/vishwash.tilala"));
        teamMemberList.add(new TeamMember("Ankur Sarode", "15BCE0785", R.drawable.x13, "https://github.com/AnkurSarode"));
        teamMemberList.add(new TeamMember("Aditya Shaha", "15BCE0227", R.drawable.x14, "https://github.com/AdityaShaha"));
        teamMemberList.add(new TeamMember("Rishabh Mittal", "13BCB0076", R.drawable.x15, "https://github.com/rishabh19038"));
        teamMemberList.add(new TeamMember("Anish Singh Walia", "13BIT0116", R.drawable.x16, "https://github.com/anishsingh20"));
    }
}