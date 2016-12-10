package com.ieeecsvit.riviera17android.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ieeecsvit.riviera17android.R;

public class LicenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void onClick(View view){
        String url = "http://www.apache.org/licenses/LICENSE-2.0.html";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
