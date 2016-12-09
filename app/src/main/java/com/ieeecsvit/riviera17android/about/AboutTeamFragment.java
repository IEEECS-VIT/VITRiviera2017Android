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


/**
 * A simple {@link Fragment} subclass.
 */
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
        teamAdapter = new TeamAdapter(teamMemberList);
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


        String[] names = {"Rishabh Chaddha", "Harsh Khara", "Abhinav",
                "Harshal Varday",
                "Tushar Narula",
                "Waris Chutani",
                "Saurabh Mathur",
                "Himanshu Jain",
                "Karishnu Poddar",
                "Vishwajeetsinh Jadeja",
                "Akanshi Srivastava",
                "Mayank Aggarwal",
                "Vishwash Tilala",
                "Ankur Sarode",
                "Aditya Shaha",
                "Rishabh Mittal",
                "Anish Singh Walia", ""};
        String[] regno = {"13BCE0034", "13BCL0139", "13BCE0680",
                "14BCE0751",
                "14BCE0336",
                "14BIT0155",
                "14BIT0180",
                "14BCE0531",
                "15BCE0318",
                "15BIT0136",
                "15BCE0325",
                "15BCE0751",
                "15BME0007",
                "15BCE0785", "15BCE0227",
                "15BCB0076",
                "15BIT0116", ""};

        String[] drawableId = new String[25];
        for (int i = 0; i < 18; i++) {
            drawableId[i] = "x" + i;
        }

        TeamMember teamMember;

        for (int i = 0; i < 17; i++) {
            int resId = getResources().getIdentifier(drawableId[i], "drawable", getActivity().getPackageName());
            teamMember = new TeamMember(names[i], regno[i], resId);
            teamMemberList.add(teamMember);
        }

    }

}
