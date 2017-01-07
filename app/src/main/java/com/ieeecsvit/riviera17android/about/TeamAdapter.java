package com.ieeecsvit.riviera17android.about;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.R;

import java.util.List;

/**
 * Created by root on 9/8/16.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.MyViewHolder> {

    private List<TeamMember> teamMemberList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView regno;
        public ImageView image;
        public LinearLayout member;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.member_name);
            regno = (TextView) view.findViewById(R.id.member_regno);
            image = (ImageView) view.findViewById(R.id.member_image);
            member = (LinearLayout) view.findViewById(R.id.ll_member);
        }
    }
    public TeamAdapter(List<TeamMember> teamMemberList, Context context){
        this.teamMemberList = teamMemberList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_member_item,parent,false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final TeamMember teamMember = teamMemberList.get(position);
        holder.name.setText(teamMember.getName());
        holder.regno.setText(teamMember.getRegno());
        holder.image.setImageResource(teamMember.getImageResId());
        holder.member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(teamMember.getLink()!=null) {
                    final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(teamMember.getLink()));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamMemberList.size();
    }
}
