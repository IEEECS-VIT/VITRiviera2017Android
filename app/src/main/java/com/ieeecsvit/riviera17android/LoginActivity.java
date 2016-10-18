package com.ieeecsvit.riviera17android;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button login,viewevent;
    EditText regno,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=(Button)findViewById(R.id.loginbutton);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/orator.ttf");
        login.setTypeface(typeface);

        viewevent=(Button)findViewById(R.id.viewevents);
        viewevent.setTypeface(typeface);

        regno=(EditText)findViewById(R.id.regno);
        pass=(EditText)findViewById(R.id.password);
        regno.setTypeface(typeface);
        pass.setTypeface(typeface);

        viewevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });




    }
}
