package com.ieeecsvit.riviera17android.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.R;
import com.ieeecsvit.riviera17android.utility.Consts;
import com.ieeecsvit.riviera17android.utility.Preferences;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView pre, work, formal, informal, cyber;
    NavigationView navigationView;
    ImageView bell;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setBackgroundColor(Color.parseColor("#302236"));

        getSupportActionBar().setTitle("");

        pre = (TextView) findViewById(R.id.pretext);
        work = (TextView) findViewById(R.id.workshoptext);
        formal = (TextView) findViewById(R.id.formaltext);
        informal = (TextView) findViewById(R.id.informaltext);
        cyber = (TextView) findViewById(R.id.sportstext);

        bell = (ImageView) toolbar.findViewById(R.id.iv_bell);

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NotificationsActivity.class);
                startActivity(intent);
            }
        });

        if (Preferences.getPrefs(Consts.ROLE_PREF, this).equals("admin")) {
            bell.setVisibility(View.VISIBLE);
        }

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

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

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (Preferences.getPrefs(Consts.LOGGED_IN_PREF, MainActivity.this).equals("0")) {
            hideItem();
        }
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
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Preferences.deletePrefs(this);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideItem() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.messageb).setVisible(false);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (id == R.id.wishlist) {
            Intent intent = new Intent(this, WishlistActivity.class);
            startActivity(intent);
        } else if (id == R.id.messageb) {
            Intent intent = new Intent(this, MessageActivity.class);
            startActivity(intent);

        } else if (id == R.id.feedback) {
            drawer.closeDrawer(GravityCompat.START);
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setType("plain/text");
            sendIntent.setData(Uri.parse("tushar.narula17@live.com"));
            sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"tushar.narula17@live.com"});
            try {
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "FEEDBACK: Riviera 2017 Android App" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            sendIntent.putExtra(Intent.EXTRA_TEXT, "");
            startActivity(sendIntent);
        } else if (id == R.id.licences) {

        } else if (id == R.id.contact) {
            Intent intent = new Intent(this, ContactActivity.class);
            startActivity(intent);

        } else if (id == R.id.about) {
            Intent intent = new Intent(this, AboutPage.class);
            startActivity(intent);
        } else if (id == R.id.login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //TODO: Change all to single function and check button id

    public void preriviera(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category", "Pre-Riviera");
        startActivity(intent);
    }

    public void workshop(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category", "Workshop");
        startActivity(intent);
    }

    public void formal(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category", "Formal");
        startActivity(intent);
    }

    public void informal(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category", "Informal");
        startActivity(intent);
    }

    public void cyber(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category", "Adventure Sports");
        startActivity(intent);
    }
}