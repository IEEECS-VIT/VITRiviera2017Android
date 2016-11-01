package com.ieeecsvit.riviera17android;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ieeecsvit.riviera17android.rest.Data;

public class MessageActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RVMessageAdapter rvMessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        recyclerView = (RecyclerView) findViewById(R.id.rv_message);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_add_message);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rvMessageAdapter = new RVMessageAdapter(RealmController.with(this).getMessages(), this);

        recyclerView.setAdapter(rvMessageAdapter);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        Data.updateMessages(this, new Data.UpdateCallback() {
            @Override
            public void onUpdate() {
                rvMessageAdapter = new RVMessageAdapter(RealmController.with(MessageActivity.this).getMessages(), MessageActivity.this);
                recyclerView.setAdapter(rvMessageAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onFailure(){}
        });
    }
}
