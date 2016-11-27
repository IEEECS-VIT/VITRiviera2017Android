package com.ieeecsvit.riviera17android;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dynamitechetan.flowinggradient.FlowingGradientClass;
import com.ieeecsvit.riviera17android.rest.Data;

public class SplashActivity extends AppCompatActivity {


    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        relativeLayout=(RelativeLayout)findViewById(R.id.activity_splash);

        FlowingGradientClass grad=new FlowingGradientClass();
        grad.setBackgroundResource(R.drawable.gradient)
                .onRelativeLayout(relativeLayout).
                setTransitionDuration(2500).start();

        RealmController.with(this);

        if(RealmController.getInstance().getEvents().isEmpty()){
            Data.updateEvents(this, new Data.UpdateCallback() {
                @Override
                public void onUpdate() {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    finish();
                    startActivity(intent);
                }
                @Override
                public void onFailure(){}
            });
        }
        else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            finish();
            startActivity(intent);

        }

    }

}
