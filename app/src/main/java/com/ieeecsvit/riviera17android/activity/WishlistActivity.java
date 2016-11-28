package com.ieeecsvit.riviera17android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ieeecsvit.riviera17android.R;
import com.ieeecsvit.riviera17android.RVEventListAdapter;
import com.ieeecsvit.riviera17android.RealmController;
import com.ieeecsvit.riviera17android.WishlistAdapter;
import com.ieeecsvit.riviera17android.models.Event;

public class WishlistActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private WishlistAdapter wishlistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_wishlist);
        recyclerView = (RecyclerView) findViewById(R.id.rv_wishlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wishlistAdapter= new WishlistAdapter(RealmController.with(this).getEvents(),this);
        recyclerView.setAdapter(new RVEventListAdapter(RealmController.with(this).getRealm().where(Event.class).equalTo("checked",true).findAll(),this,true));

    }
}
