package com.ieeecsvit.riviera17android;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.ieeecsvit.riviera17android.utility.Consts;
import com.ieeecsvit.riviera17android.utility.Preferences;

public class EventActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        // Create the adapter that will return a fragment for each of the three
        //
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this, getIntent().getStringExtra("eventId"));

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Event Details");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this,R.color.colorPrimary));


        NestedScrollView scrollView = (NestedScrollView) findViewById (R.id.scroll);
        scrollView.setFillViewport (true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(Preferences.getPrefs(Consts.ROLE_PREF,this).equals("admin") || Preferences.getPrefs(Consts.ROLE_PREF,this).equals("coordinator")){
            fab.setVisibility(View.VISIBLE);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventActivity.this, EventEditActivity.class);
                intent.putExtra(Consts.EVENT_BUNDLE, getIntent().getStringExtra("eventId"));
                startActivity(intent);
            }
        });
    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private Activity context;
        private String eventId;

        public SectionsPagerAdapter(FragmentManager fm, Activity context, String eventId) {
            super(fm);

            this.context = context;
            this.eventId = eventId;
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0) {
                return EventDetailsFragment.newInstance(eventId);
            }
            else{
                return EventCoordinatorsFragment.newInstance(eventId);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "DETAILS";
                case 1:
                    return "COORDINATORS";
            }
            return null;
        }
    }
}
