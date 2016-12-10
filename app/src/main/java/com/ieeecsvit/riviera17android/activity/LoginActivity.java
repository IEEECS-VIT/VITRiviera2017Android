package com.ieeecsvit.riviera17android.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dynamitechetan.flowinggradient.FlowingGradientClass;
import com.ieeecsvit.riviera17android.R;
import com.ieeecsvit.riviera17android.rest.Auth;
import com.ieeecsvit.riviera17android.utility.Consts;
import com.ieeecsvit.riviera17android.utility.Preferences;

public class LoginActivity extends AppCompatActivity {

    ImageView background;
    Button login, viewevent;
    EditText regno, pass;
    RelativeLayout relativeLayout;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Preferences.getPrefs(Consts.LOGGED_IN_PREF, this).equals("1")) {
            Toast.makeText(this, "Already Logged In! Please Log Out first!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        relativeLayout = (RelativeLayout) findViewById(R.id.activity_login);
        FlowingGradientClass grad = new FlowingGradientClass();
        grad.setBackgroundResource(R.drawable.gradient)
                .onRelativeLayout(relativeLayout).
                setTransitionDuration(2500).start();

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);

        login = (Button) findViewById(R.id.loginbutton);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/orator.ttf");
        login.setTypeface(typeface);

        viewevent = (Button) findViewById(R.id.viewevents);
        viewevent.setTypeface(typeface);

        regno = (EditText) findViewById(R.id.regno);
        pass = (EditText) findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("Logging in. Please wait...");
                dialog.setIndeterminate(true);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                Auth.login(regno.getText().toString(), pass.getText().toString(), LoginActivity.this, new Auth.OnLoginCallback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.INVISIBLE);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                        dialog.hide();
                    }

                    @Override
                    public void onFailure() {
                        progressBar.setVisibility(View.INVISIBLE);
                        new AlertDialog.Builder(LoginActivity.this)
                                .setMessage("Invalid Credentials!")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // do nothing
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        dialog.hide();
                    }
                });
            }
        });

        viewevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.setPrefs(Consts.LOGGED_IN_PREF, "0", LoginActivity.this);
                finish();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}
