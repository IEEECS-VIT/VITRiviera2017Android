package com.ieeecsvit.riviera17android.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.R;
import com.ieeecsvit.riviera17android.models.Message;

/**
 * Created by Karishnu Poddar on 16/10/2016.
 */

public class MessageListItem extends LinearLayout{

    TextView tvSentBy, tvSentTo, tvMessage;

    public void initialize() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.item_message_custom, this, true);

        tvSentBy = (TextView) findViewById(R.id.tv_sent_by);
        tvMessage = (TextView) findViewById(R.id.tv_message);
    }

    public void setValues(Message message) {
        tvSentBy.setText(message.getFrom().getName() + " (" + message.getFrom().getUsername() + ")");
        tvMessage.setText(message.getText());
    }

    public MessageListItem(Context context) {
        super(context);
        initialize();
    }

    public MessageListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public MessageListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }
}
