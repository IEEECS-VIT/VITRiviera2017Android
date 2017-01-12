package com.ieeecsvit.riviera17android.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.dynamitechetan.flowinggradient.FlowingGradientClass;
import com.ieeecsvit.riviera17android.R;
import com.ieeecsvit.riviera17android.RealmController;
import com.ieeecsvit.riviera17android.rest.Data;
import com.ieeecsvit.riviera17android.utility.Consts;
import com.ieeecsvit.riviera17android.utility.Preferences;
import com.ieeecsvit.riviera17android.utility.UtilityMethods;

import io.realm.RealmConfiguration;

public class SplashActivity extends AppCompatActivity {


    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        relativeLayout= (RelativeLayout) findViewById(R.id.activity_splash);

        FlowingGradientClass grad=new FlowingGradientClass();
        grad.setBackgroundResource(R.drawable.gradient)
                .onRelativeLayout(relativeLayout).
                setTransitionDuration(2500).start();

        RealmController.with(this);

        if(Preferences.getPrefs(Consts.LOGGED_IN_PREF, this).equals("notfound")){
            Preferences.setPrefs(Consts.LOGGED_IN_PREF, "0", this);
        }

        if(UtilityMethods.isNetworkAvailable()){
            Data.updateEvents(this, new Data.UpdateCallback() {
                @Override
                public void onUpdate() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                @Override
                public void onFailure(){}
            });
        }
        else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

}
