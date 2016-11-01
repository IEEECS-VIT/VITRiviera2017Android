package com.ieeecsvit.riviera17android;

/**
 * Created by Karishnu Poddar on 16/10/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ieeecsvit.riviera17android.customviews.EventListItem;
import com.ieeecsvit.riviera17android.models.Event;

import java.util.List;

public class RVEventListAdapter extends RecyclerView.Adapter<RVEventListAdapter.EventItemViewHolder> {

    private List<Event> eventsList;
    private Activity context;
    Boolean clickable;

    public class EventItemViewHolder extends RecyclerView.ViewHolder {
        public EventListItem eventListItem;

        public EventItemViewHolder(View view) {
            super(view);
            eventListItem = (EventListItem) view.findViewById(R.id.event_list_item);
        }
    }

    public RVEventListAdapter(List<Event> eventsList, Activity context, Boolean clickable) {
        this.eventsList = eventsList;
        this.context = context;
        this.clickable = clickable;
    }

    @Override
    public EventItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event_list_rv, parent, false);

        return new EventItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EventItemViewHolder holder, final int position) {
        final Event event = eventsList.get(position);
        holder.eventListItem.setValues(event.checked, event.eventName, event.eventChapterName);
        holder.eventListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickable) {
                    Intent intent = new Intent(context, EventActivity.class);
                    intent.putExtra("eventId", event.id);
                    context.startActivity(intent);
                }
                else{
                    Intent intent = new Intent();
                    intent.putExtra("eventId",event.id);
                    context.setResult(Activity.RESULT_OK,intent);
                    context.finish();
                }
            }
        });

        holder.eventListItem.getCheckButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(event.checked==null){
                    RealmController.with(context).setFavourite(event.id,true);
                    holder.eventListItem.setCheck(true);
                }
                else {
                    if (event.checked) {
                        RealmController.with(context).setFavourite(event.id, false);
                        holder.eventListItem.setCheck(false);
                    } else {
                        RealmController.with(context).setFavourite(event.id, true);
                        holder.eventListItem.setCheck(true);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
