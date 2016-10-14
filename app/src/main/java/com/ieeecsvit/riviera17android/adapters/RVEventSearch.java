package com.ieeecsvit.riviera17android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.R;
import com.ieeecsvit.riviera17android.models.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karishnu Poddar on 14/10/2016.
 */
public class RVEventSearch extends RecyclerView.Adapter<RVEventSearch.EventSearchViewHolder> implements Filterable {

    private List<Event> eventsList;
    private List<Event> orig;

    public class EventSearchViewHolder extends RecyclerView.ViewHolder {
        public TextView eventName;

        public EventSearchViewHolder(View view) {
            super(view);

            eventName = (TextView) view.findViewById(R.id.eventName);
        }
    }

    public RVEventSearch(List<Event> eventsList) {
        this.eventsList = eventsList;
    }

    @Override
    public EventSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_event_list, parent, false);

        return new EventSearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventSearchViewHolder holder, int position) {
        Event event = eventsList.get(position);

        holder.eventName.setText(event.eventName);
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<Event> results = new ArrayList<Event>();
                if (orig == null)
                    orig = eventsList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final Event g : orig) {
                            if (g.eventName.toLowerCase().contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                eventsList = (ArrayList<Event>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}