package com.ieeecsvit.riviera17android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ieeecsvit.riviera17android.rest.Data;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Data.updateEvents(this);
    }
}
