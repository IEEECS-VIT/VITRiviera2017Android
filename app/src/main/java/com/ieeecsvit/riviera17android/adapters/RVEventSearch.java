package com.ieeecsvit.riviera17android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.R;
import com.ieeecsvit.riviera17android.models.Event;

import java.util.List;

/**
 * Created by Karishnu Poddar on 14/10/2016.
 */
public class RVEventSearch extends RecyclerView.Adapter<RVEventSearch.EventSearchViewHolder> {

    private List<Event> eventsList;

    public class EventSearchViewHolder extends RecyclerView.ViewHolder {
        public TextView eventName;

        public EventSearchViewHolder(View view){
            super(view);

            eventName = (TextView) view.findViewById(R.id.eventName);
        }
    }

    public RVEventSearch(List<Event> eventsList){
        this.eventsList = eventsList;
    }

    @Override
    public EventSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_event_list, parent, false);

        return new EventSearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventSearchViewHolder holder, int position){
        Event event = eventsList.get(position);

        holder.eventName.setText(event.eventName);
    }

    @Override
    public int getItemCount(){
        return eventsList.size();
    }
}
