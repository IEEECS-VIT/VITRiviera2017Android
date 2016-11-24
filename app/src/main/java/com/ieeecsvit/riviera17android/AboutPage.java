package com.ieeecsvit.riviera17android;

import android.graphics.Point;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;


import com.ieeecsvit.riviera17android.about.AboutTeam2Fragment;
import com.roughike.bottombar.R;
import com.ieeecsvit.riviera17android.about.AboutTeamFragment;
import com.ieeecsvit.riviera17android.about.AboutUniversityFragment;
public class AboutPage extends AppCompatActivity {

    int height,width;
    private R bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        height = size.y;
        width = size.x;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AppBarLayout appBarLayout = (AppBarLayout)findViewById(R.id.app_bar_about);
        appBarLayout.setLayoutParams(new CoordinatorLayout.LayoutParams(width,height/3));
        final NestedScrollView scrollView = (NestedScrollView)findViewById(R.id.about_nested_scrolling);
        scrollView.setSmoothScrollingEnabled(true);
        bottomBar = R.attachShy((CoordinatorLayout) findViewById(R.id.about_coordinator),findViewById(R.id.about_nested_scrolling),savedInstanceState);
        bottomBar.setMaxFixedTabs(3);
        bottomBar.noTopOffset();
        bottomBar.setItems(R.menu.bottombar_about);


        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(int menuItemId) {
                if(menuItemId==R.id.bottom_bar_about_riviera){
                    Fragment fragment = null;
                    Class fragmentClass;
                    fragmentClass = AboutGravitasFragment.class;
                    try{
                        fragment = (Fragment)fragmentClass.newInstance();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.about_fragment_container,fragment).commit();
                }else if(menuItemId==R.id.bottom_bar_about_university){
                    Fragment fragment = null;
                    Class fragmentClass;
                    fragmentClass = AboutUniversityFragment.class;
                    try{
                        fragment = (Fragment)fragmentClass.newInstance();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.about_fragment_container,fragment).commit();
                }else if(menuItemId==R.id.bottom_bar_about_team){
                    Fragment fragment = null;
                    Class fragmentClass;
                    fragmentClass = AboutTeamFragment.class;
                    try{
                        fragment = (Fragment)fragmentClass.newInstance();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.about_fragment_container,fragment).commit();
                }else if(menuItemId==R.id.bottom_bar_about_team2){
                    Fragment fragment = null;
                    Class fragmentClass;
                    fragmentClass = AboutTeam2Fragment.class;
                    try{
                        fragment = (Fragment)fragmentClass.newInstance();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.about_fragment_container,fragment).commit();
                }
            }

            @Override
            public void onMenuTabReSelected(int menuItemId) {
                if(menuItemId==R.id.bottom_bar_about_riviera){
                    scrollView.fullScroll(NestedScrollView.FOCUS_UP);
                }else if(menuItemId==R.id.bottom_bar_about_university){
                    scrollView.fullScroll(NestedScrollView.FOCUS_UP);
                }else if(menuItemId==R.id.bottom_bar_about_team){
                    scrollView.fullScroll(NestedScrollView.FOCUS_UP);
                }
            }
        });




    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState(outState);

    }
}
