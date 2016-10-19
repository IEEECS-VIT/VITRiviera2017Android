package com.ieeecsvit.riviera17android;

/**
 * Created by Karishnu Poddar on 16/10/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ieeecsvit.riviera17android.customviews.EventListItem;
import com.ieeecsvit.riviera17android.models.Event;

import java.util.List;

public class RVEventListAdapter extends RecyclerView.Adapter<RVEventListAdapter.EventItemViewHolder> {

    private List<Event> eventsList;
    private Context context;

    public class EventItemViewHolder extends RecyclerView.ViewHolder {
        public EventListItem eventListItem;

        public EventItemViewHolder(View view) {
            super(view);
            eventListItem = (EventListItem) view.findViewById(R.id.event_list_item);
        }
    }


    public RVEventListAdapter(List<Event> eventsList, Context context) {
        this.eventsList = eventsList;
        this.context = context;
    }

    @Override
    public EventItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event_list_rv, parent, false);

        return new EventItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventItemViewHolder holder, final int position) {
        holder.eventListItem.setValues(eventsList.get(position).checked, eventsList.get(position).eventName, eventsList.get(position).eventChapterName);
        holder.eventListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EventActivity.class);
                intent.putExtra("eventId", eventsList.get(position).id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}