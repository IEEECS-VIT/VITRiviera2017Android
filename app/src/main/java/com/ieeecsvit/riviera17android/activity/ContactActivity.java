package com.ieeecsvit.riviera17android.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.R;

public class ContactActivity extends AppCompatActivity {

    CardView cardView1, cardView2;
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cardView1 = (CardView) findViewById(R.id.cv_contact_1);
        cardView2 = (CardView) findViewById(R.id.cv_contact_2);

        textView1 = (TextView) findViewById(R.id.tv_contact_1);
        textView2 = (TextView) findViewById(R.id.tv_contact_2);

        final Intent intent = new Intent(Intent.ACTION_DIAL);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberToCall = "tel:+919629766583";
                intent.setData(Uri.parse(numberToCall));
                startActivity(intent);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberToCall = "tel:+919920555055";
                intent.setData(Uri.parse(numberToCall));
                startActivity(intent);
            }
        });
    }
}
