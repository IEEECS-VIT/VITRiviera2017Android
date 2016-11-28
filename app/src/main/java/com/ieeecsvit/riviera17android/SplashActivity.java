package com.ieeecsvit.riviera17android;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.ieeecsvit.riviera17android.rest.Data;
import com.ieeecsvit.riviera17android.utility.UtilityMethods;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RealmController.with(this);

        if(UtilityMethods.isNetworkAvailable()){
            Data.updateEvents(this, new Data.UpdateCallback() {
                @Override
                public void onUpdate() {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                @Override
                public void onFailure(){}
            });
        }
        else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
