package com.ieeecsvit.riviera17android;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dynamitechetan.flowinggradient.FlowingGradient;
import com.dynamitechetan.flowinggradient.FlowingGradientClass;
import com.ieeecsvit.riviera17android.rest.Auth;
import com.ieeecsvit.riviera17android.utility.Consts;
import com.ieeecsvit.riviera17android.utility.Preferences;

public class LoginActivity extends AppCompatActivity {

    Button login,viewevent;
    EditText regno,pass;
    ImageView background;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        relativeLayout=(RelativeLayout)findViewById(R.id.activity_login);
        FlowingGradientClass grad=new FlowingGradientClass();
        grad.setBackgroundResource(R.drawable.gradient)
        .onRelativeLayout(relativeLayout).
        setTransitionDuration(2500).start();

/*
        background=(ImageView)findViewById(R.id.backgroundimage);
        background.setBackgroundResource(R.drawable.gradient);

        Animation fadeanim=AnimationUtils.loadAnimation(this,R.anim.fadein);
        background.startAnimation(fadeanim);

        AnimationDrawable changeBack=(AnimationDrawable)background.getBackground();
        changeBack.start();
*/

        login=(Button)findViewById(R.id.loginbutton);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/orator.ttf");
        login.setTypeface(typeface);

        viewevent=(Button)findViewById(R.id.viewevents);
        viewevent.setTypeface(typeface);

        regno=(EditText)findViewById(R.id.regno);
        pass=(EditText)findViewById(R.id.password);
        regno.setTypeface(typeface);
        pass.setTypeface(typeface);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.login(regno.getText().toString(), pass.getText().toString(), LoginActivity.this, new Auth.OnLoginCallback() {
                    @Override
                    public void onSuccess() {
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        Preferences.setPrefs(Consts.LOGGED_IN_PREF,"1",LoginActivity.this);
                    }
                });
            }
        });

        viewevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                Preferences.setPrefs(Consts.LOGGED_IN_PREF,"0",LoginActivity.this);
            }
        });
    }
}
