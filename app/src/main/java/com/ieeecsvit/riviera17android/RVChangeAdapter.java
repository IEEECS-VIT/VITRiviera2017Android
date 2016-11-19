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

import com.ieeecsvit.riviera17android.models.Change;
import com.ieeecsvit.riviera17android.models.Message;

import java.util.List;

class RVChangeAdapter extends RecyclerView.Adapter<RVChangeAdapter.ChangeItemViewHolder> {

    private List<Change> changeList;
    private Activity context;

    class ChangeItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvChangeType;
        TextView tvChangeDesc;

        ChangeItemViewHolder(View view) {
            super(view);
            tvChangeType = (TextView) view.findViewById(R.id.tvChangeType);
            tvChangeDesc = (TextView) view.findViewById(R.id.tvChangeDescription);
        }
    }


    public RVChangeAdapter(List<Change> changeList, Activity context) {
        this.changeList = changeList;
        this.context = context;
    }

    @Override
    public ChangeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_change, parent, false);

        return new ChangeItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChangeItemViewHolder holder, final int position) {
        holder.tvChangeType.setText(changeList.get(position).changeField);
        holder.tvChangeDesc.setText(changeList.get(position).changeValue);
    }

    @Override
    public int getItemCount() {
        return changeList.size();
    }
}
