package com.ieeecsvit.riviera17android;

/**
 * Created by Karishnu Poddar on 16/10/2016.
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.models.ChangeRequests;

import java.util.List;

class RVChangeResponseAdapter extends RecyclerView.Adapter<RVChangeResponseAdapter.ChangeResponseItemViewHolder> {

    private List<ChangeRequests> changeList;
    private Activity context;
    ViewGroup parent;

    class ChangeResponseItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvEvent;
        RecyclerView recyclerViewChanges;

        ChangeResponseItemViewHolder(View view) {
            super(view);

            tvEvent = (TextView) view.findViewById(R.id.tvEvent);
            recyclerViewChanges = (RecyclerView) view.findViewById(R.id.rvChanges);
            recyclerViewChanges.setLayoutManager(new CustomLinearLayoutManager(context));
        }
    }

    RVChangeResponseAdapter(List<ChangeRequests> changeList, Activity context) {
        this.changeList = changeList;
        this.context = context;
    }

    @Override
    public ChangeResponseItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_change_response, parent, false);

        return new ChangeResponseItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChangeResponseItemViewHolder holder, final int position) {
        holder.tvEvent.setText(changeList.get(position).eventFor);
        holder.recyclerViewChanges.setAdapter(new RVChangeAdapter(changeList.get(position).changes, context));
    }

    @Override
    public int getItemCount() {
        return changeList.size();
    }
}
