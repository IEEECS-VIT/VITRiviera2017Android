package com.ieeecsvit.riviera17android;

/**
 * Created by Karishnu Poddar on 28/10/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.activity.CategoryActivity;
import com.ieeecsvit.riviera17android.utility.Consts;

import java.util.List;

public class SubCategoryListAdapter extends BaseAdapter {
    private Activity activity;
    private List<String> list;
    private LayoutInflater inflater;
    private String category;

    public SubCategoryListAdapter(Activity activity, List<String> list, String category) {
        this.activity = activity;
        this.list = list;
        this.category = category;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int location) {
        return list.get(location);
    }

    @Override
    public long getItemId(int position) {
            return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_sub_category, null);

        TextView string = (TextView) convertView.findViewById(R.id.tv_string);
        string.setText(list.get(position));
        final LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.ll_sub_category);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, CategoryActivity.class);
                intent.putExtra(Consts.INTENT_CATEGORY, category);
                intent.putExtra(Consts.INTENT_SUB_CATEGORY, list.get(position));
                activity.startActivity(intent);
            }
        });

        return convertView;
    }

}