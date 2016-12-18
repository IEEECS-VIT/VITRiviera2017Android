package com.ieeecsvit.riviera17android.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;

import com.ieeecsvit.riviera17android.R;
import com.ieeecsvit.riviera17android.about.AboutTeamFragment;

public class AboutPage extends AppCompatActivity {

    int height, width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        height = size.y;
        width = size.x;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_about);
        appBarLayout.setLayoutParams(new CoordinatorLayout.LayoutParams(width, height / 3));
        final NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.about_nested_scrolling);
        scrollView.setSmoothScrollingEnabled(true);

        Fragment fragment = null;
        Class fragmentClass;
        fragmentClass = AboutTeamFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.about_fragment_container, fragment).commit();
    }
}
