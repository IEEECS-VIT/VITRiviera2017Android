package com.ieeecsvit.riviera17android;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity {

    ImageView catimage,back_image;
    String getCat;
    TextView catTextView;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getCat=getIntent().getStringExtra("category");

        catTextView=(TextView)findViewById(R.id.typeCategory);
        catTextView.setText(getCat);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        view=(View)findViewById(R.id.catLine);

        back_image=(ImageView)findViewById(R.id.back_image);
        catimage=(ImageView)findViewById(R.id.categoryimage);

        if(getCat.equals("Pre-Riviera")){
            catimage.setImageResource(R.drawable.preriv_ic);
            back_image.setImageResource(R.drawable.preriv_back);
            view.setBackgroundColor(Color.parseColor("#FDA736"));

        }
        else if(getCat.equals("Workshop")){
            catimage.setImageResource(R.drawable.preriv_ic);
            back_image.setImageResource(R.drawable.workshop_back);

            view.setBackgroundColor(Color.parseColor("#FDA736"));
        }
        else if(getCat.equals("Formal")){
            catimage.setImageResource(R.drawable.preriv_ic);
            back_image.setImageResource(R.drawable.formal_back);
            view.setBackgroundColor(Color.parseColor("#FDA736"));

        }
        else if(getCat.equals("Informal")){
            catimage.setImageResource(R.drawable.preriv_ic);
            back_image.setImageResource(R.drawable.informal_back);
            view.setBackgroundColor(Color.parseColor("#FDA736"));

        }


/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

/*        AppBarLayout scrollView = (AppBarLayout)findViewById(R.id.app_bar);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)scrollView.getLayoutParams();
        params.setBehavior(new NestedScrollViewBehavior());*/

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new RVEventListAdapter(RealmController.with(this).getEvents(getIntent()
                .getStringExtra("category")), this));

        recyclerView.setAdapter(new RVEventListAdapter(RealmController.with(this).getEvents(getIntent().getStringExtra("category")),this,true));
    }
}
