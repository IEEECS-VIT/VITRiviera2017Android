package com.ieeecsvit.riviera17android;

/**
 * Created by Karishnu Poddar on 16/10/2016.
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ieeecsvit.riviera17android.customviews.MessageListItem;
import com.ieeecsvit.riviera17android.models.Message;

import java.util.List;

class RVMessageAdapter extends RecyclerView.Adapter<RVMessageAdapter.EventItemViewHolder> {

    private List<Message> messageList;
    private Activity context;

    class EventItemViewHolder extends RecyclerView.ViewHolder {
        MessageListItem messageListItem;

        EventItemViewHolder(View view) {
            super(view);
            messageListItem = (MessageListItem) view.findViewById(R.id.item_message);
        }
    }


    public RVMessageAdapter(List<Message> messageList, Activity context) {
        this.messageList = messageList;
        this.context = context;
    }

    @Override
    public EventItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message_custom, parent, false);

        return new EventItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EventItemViewHolder holder, final int position) {
        holder.messageListItem.setValues(messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
