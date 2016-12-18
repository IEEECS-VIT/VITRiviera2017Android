package com.ieeecsvit.riviera17android.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
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
import android.widget.Toast;

import com.ieeecsvit.riviera17android.CustomDialogClass;
import com.ieeecsvit.riviera17android.R;
import com.ieeecsvit.riviera17android.utility.Consts;
import com.ieeecsvit.riviera17android.utility.Preferences;

import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView pre, work, formal, informal, cyber, premium;
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

        if (Preferences.getPrefs(Consts.SHOW_WISHLIST_PREF, this).equals(Consts.TRUE) || Preferences.getPrefs(Consts.SHOW_WISHLIST_PREF, this).equals(Consts.NOT_FOUND)) {
            CustomDialogClass cdd = new CustomDialogClass(this);
            cdd.show();
        }

        pre = (TextView) findViewById(R.id.pretext);
        work = (TextView) findViewById(R.id.workshoptext);
        formal = (TextView) findViewById(R.id.formaltext);
        informal = (TextView) findViewById(R.id.informaltext);
        cyber = (TextView) findViewById(R.id.sportstext);
        premium = (TextView) findViewById(R.id.premiumtext);

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

        premium.setTypeface(typeface);
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

    public Intent createEmailOnlyChooserIntent(Intent source,
                                               CharSequence chooserTitle) {
        Stack<Intent> intents = new Stack<Intent>();
        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
                "tushar.narula17@live.com", null));
        List<ResolveInfo> activities = getPackageManager()
                .queryIntentActivities(i, 0);

        for (ResolveInfo ri : activities) {
            Intent target = new Intent(source);
            target.setPackage(ri.activityInfo.packageName);
            intents.add(target);
        }

        if (!intents.isEmpty()) {
            Intent chooserIntent = Intent.createChooser(intents.remove(0),
                    chooserTitle);
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                    intents.toArray(new Parcelable[intents.size()]));

            return chooserIntent;
        } else {
            return Intent.createChooser(source, chooserTitle);
        }
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
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("*/*");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"tushar.narula17@live.com", "karishnu@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "Riviera 2017 Android App Feedback");
            startActivity(createEmailOnlyChooserIntent(i, "Send Feedback via email"));

        } else if (id == R.id.licences) {
            Intent intent = new Intent(this, LicenseActivity.class);
            startActivity(intent);

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

    public void categoryClick(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        switch (view.getId()) {
            case R.id.cv_premium_button:
                intent.putExtra("category", "Premium");
                break;
            case R.id.cv_preriviera_button:
                intent.putExtra("category", "Pre-Riviera");
                break;
            case R.id.cv_workshop_button:
                intent.putExtra("category", "Workshop");
                break;
            case R.id.cv_formal_button:
                intent.putExtra("category", "Formal");
                break;
            case R.id.cv_informal_button:
                intent.putExtra("category", "Informal");
                break;
            case R.id.cv_sports_button:
                intent.putExtra("category", "Formal");
                intent.putExtra(Consts.INTENT_SUB_CATEGORY, "Adventure Sports");
                break;
        }
        startActivity(intent);
    }
}