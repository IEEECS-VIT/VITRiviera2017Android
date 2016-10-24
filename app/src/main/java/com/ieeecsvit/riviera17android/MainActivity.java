package com.ieeecsvit.riviera17android;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView pre,work,formal,informal,cyber;
    CardView prec,workc,formalc,informalc,cyberc;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setBackgroundColor(Color.parseColor("#302236"));

        getSupportActionBar().setTitle("Riviera '17");

        pre=(TextView)findViewById(R.id.pretext);
        work=(TextView)findViewById(R.id.workshoptext);
        formal=(TextView)findViewById(R.id.formaltext);
        informal=(TextView)findViewById(R.id.informaltext);
        cyber=(TextView)findViewById(R.id.cybertext);

        Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        pre.setTypeface(typeface);
        work.setTypeface(typeface);
        formal.setTypeface(typeface);
        informal.setTypeface(typeface);
        cyber.setTypeface(typeface);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.wishlist) {
            // Handle the camera action
        } else if (id == R.id.messageb) {

        } else if (id == R.id.requestb) {

        } else if (id == R.id.register) {

        } else if (id == R.id.feedback) {

        } else if (id == R.id.licences) {

        }
        else if (id == R.id.contact) {

        }
        else if (id == R.id.about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //TODO: Change all to single function and check button id

    public void preriviera(View view){
        Toast.makeText(this, "pre", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category","Pre-Riviera");
        startActivity(intent);
    }

    public void workshop(View view){
        Toast.makeText(this, "work", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category","Workshop");
        startActivity(intent);

    }
    public void formal(View view){
        Toast.makeText(this, "formal", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category","Formal");
        startActivity(intent);

    }
    public void informal(View view){
        Toast.makeText(this, "informal", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category","Informal");
        startActivity(intent);


    }
    public void cyber(View view){
        //Toast.makeText(this, "cyber", Toast.LENGTH_SHORT).show();

    }
}
