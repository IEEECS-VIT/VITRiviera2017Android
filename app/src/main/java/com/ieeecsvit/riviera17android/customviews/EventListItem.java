package com.ieeecsvit.riviera17android.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.utility.CapitalizedTextView;
import com.ieeecsvit.riviera17android.R;

/**
 * Created by Karishnu Poddar on 16/10/2016.
 */

public class EventListItem extends LinearLayout {

    ImageView checkbox;
    CapitalizedTextView event_name;
    TextView event_chap_name;

    public void initialize() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.item_event_list, this, true);

        checkbox = (ImageView) findViewById(R.id.iv_checkbox_event);
        event_name = (CapitalizedTextView) findViewById(R.id.tv_event_title);
        event_chap_name = (TextView) findViewById(R.id.tv_event_chap_name);
    }

    public void setValues(Boolean checked, String event_name_string, String event_chap_name_string) {
        setCheck(checked);
        event_name.setText(event_name_string);
        event_chap_name.setText(event_chap_name_string);
    }

    public ImageView getCheckButton() {
        return checkbox;
    }

    public void setCheck(Boolean checked) {
        if (checked != null) {
            if (checked) {
                checkbox.setImageResource(R.drawable.ic_star_black_24dp);
            } else {
                checkbox.setImageResource(R.drawable.ic_star_border_black_24dp);
            }
        } else {
            checkbox.setImageResource(R.drawable.ic_star_border_black_24dp);
        }
    }

    public EventListItem(Context context) {
        super(context);
        initialize();
    }

    public EventListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public EventListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }
}
