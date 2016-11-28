package com.ieeecsvit.riviera17android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.ieeecsvit.riviera17android.models.PerEventResponse;
import com.ieeecsvit.riviera17android.rest.ApiClient;
import com.ieeecsvit.riviera17android.rest.ApiInterface;
import com.ieeecsvit.riviera17android.utility.Consts;
import com.ieeecsvit.riviera17android.utility.Preferences;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        Realm.init(this);

        final Realm realm = Realm.getDefaultInstance();

        ApiInterface apiInterface = new ApiClient().getClient(this).create(ApiInterface.class);
        Call<PerEventResponse> perEventResponseCall = apiInterface.getEvent(getIntent().getStringExtra("eventId"));

        perEventResponseCall.enqueue(new Callback<PerEventResponse>() {
            @Override
            public void onResponse(Call<PerEventResponse> call, Response<PerEventResponse> response) {
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(response.body().event);
                realm.commitTransaction();
                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), EventActivity.this, getIntent().getStringExtra("eventId"));
                mViewPager.setAdapter(mSectionsPagerAdapter);
            }

            @Override
            public void onFailure(Call<PerEventResponse> call, Throwable t) {

            }
        });
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

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
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        finish();
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
