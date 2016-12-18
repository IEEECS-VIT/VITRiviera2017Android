package com.ieeecsvit.riviera17android;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.ieeecsvit.riviera17android.utility.Consts;
import com.ieeecsvit.riviera17android.utility.Preferences;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    Activity c;
    Button yes, no;

    public CustomDialogClass(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_wishlist);
        yes = (Button) findViewById(R.id.bt_show_again);
        no = (Button) findViewById(R.id.bt_hide);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_show_again:
                Preferences.setPrefs(Consts.SHOW_WISHLIST_PREF, Consts.TRUE, c);
                dismiss();
                break;
            case R.id.bt_hide:
                Preferences.setPrefs(Consts.SHOW_WISHLIST_PREF, Consts.FALSE, c);
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}