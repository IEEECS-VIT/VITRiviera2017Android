package com.ieeecsvit.riviera17android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.models.Event;

import java.util.List;

/**
 * Created by Akanshi on 10/18/2016.
 */
public class Wishlist_Adapter extends RecyclerView.Adapter<Wishlist_Adapter.VH> {
    private Context context;
    private List<Event> events;

    public class VH extends RecyclerView.ViewHolder{
        public TextView clubName, eventName;
        ImageView deleteButton;

        public VH(View view) {
            super(view);
            clubName = (TextView) itemView.findViewById(R.id.chaptername);
            eventName = (TextView) itemView.findViewById(R.id.eventname);
            deleteButton = (ImageView) itemView.findViewById(R.id.deletes);
        }
    }

    public Wishlist_Adapter(List<Event> events, Context context) {
        this.context = context;
        this.events = events;
    }


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new VH(rootView);
    }

    @Override
    public void onBindViewHolder(VH holder, int position){
    holder.eventName.setText(events.get(position).eventName);
    holder.clubName.setText(events.get(position).eventChapterName);
    }

    @Override
    public int getItemCount(){ return events.size(); }
}